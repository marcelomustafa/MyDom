package br.com.mariapuri.mydom.config.security.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mariapuri.mydom.app.domain.model.RoleModel;
import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.service.RoleService;
import br.com.mariapuri.mydom.app.service.UserService;
import br.com.mariapuri.mydom.config.security.auth.AuthTokenProvider;
import br.com.mariapuri.mydom.config.security.payload.RefreshToken;
import br.com.mariapuri.mydom.config.security.payload.request.LoginRequest;
import br.com.mariapuri.mydom.config.security.payload.request.SignupRequest;
import br.com.mariapuri.mydom.config.security.payload.response.MessageResponse;
import br.com.mariapuri.mydom.config.security.payload.response.UserInfoResponse;
import br.com.mariapuri.mydom.config.security.service.RefreshTokenService;
import br.com.mariapuri.mydom.config.security.service.UserDetailsImpl;
import br.com.mariapuri.mydom.enums.RoleNameType;
import br.com.mariapuri.mydom.exceptionhandler.trowexceptionhandler.TokenRefreshException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	RefreshTokenService refreshTokenService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthTokenProvider tokenProvider;

	@GetMapping("/me")
	public ResponseEntity<?> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
		Map<Object, Object> model = new HashMap<>();
		model.put("username", userDetails.getUsername());
		model.put("roles", userDetails.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority())
		    .collect(Collectors.toList()));
		return ResponseEntity.status(HttpStatus.OK).body(model);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {

		try {

			String username = loginRequest.username();
			String password = loginRequest.password();
			
			
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
			    .collect(Collectors.toList());
			
			
			RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
			
			switch (loginRequest.authTokenType()) {
			case TOKEN:
				String token = tokenProvider.createToken(username, this.userService.findByUserName(username)
				    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
				
//				Map<Object, Object> model = new HashMap<>();
//				model.put("username", username);
//				model.put("token", token);
//				return ResponseEntity.status(HttpStatus.OK).body(model);
				
		    return ResponseEntity.status(HttpStatus.OK)
            .body(new UserInfoResponse(userDetails.getId(), 
            													 userDetails.getUsername(), 
            													 userDetails.getEmail(), 
            													 token, 
            													 roles));						

			case COOKIE:
				
				ResponseCookie cookie = tokenProvider.createTokenCookie(username, this.userService.findByUserName(username)
				    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());				
				
				ResponseCookie jwtRefreshCookie = tokenProvider.generateRefreshJwtCookie(refreshToken.getToken());
				
				
		    return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
            .body(new UserInfoResponse(userDetails.getId(), 
            													 userDetails.getUsername(), 
            													 userDetails.getEmail(), 
            													 "", 
            													 roles));				
				
			default:
				return ResponseEntity.badRequest().body("Type of token not valid.");
			}

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}

	}
	
  @PostMapping("/refreshcookie")
  public ResponseEntity<?> refreshcookie(HttpServletRequest request) {
    String refreshToken = tokenProvider.getJwtRefreshFromCookies(request);
    
    if ((refreshToken != null) && (refreshToken.length() > 0)) {
      return refreshTokenService.findByToken(refreshToken)
          .map(refreshTokenService::verifyExpiration)
          .map(RefreshToken::getUser)
          .map(user -> {

    				ResponseCookie cookie = tokenProvider.createTokenCookie(user.getUserName(), this.userService.findByUserName(user.getUserName())
    				    .orElseThrow(() -> new UsernameNotFoundException("Username " + user.getUserName() + "not found")).getRoles());	         	
            
            return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Token is refreshed successfully!"));
          })
          .orElseThrow(() -> new TokenRefreshException(refreshToken,
              "Refresh token is not in database!"));
    }
    
    return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
  }

  
  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
  	String token = tokenProvider.resolveTokenCookie((HttpServletRequest) request);
  	return null;
  }
  
  
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userService.existsByUserName(signUpRequest.username())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.existsByEmail(signUpRequest.email())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		UserModel user = new UserModel(signUpRequest.name(), signUpRequest.documento(), signUpRequest.email(),
		    signUpRequest.username(), encoder.encode(signUpRequest.password()));

		Set<String> strRoles = signUpRequest.role();
		Set<RoleModel> roles = new HashSet<>();

		if (strRoles == null) {
			RoleModel userRole = roleService.findByName(RoleNameType.ROLE_USER)
			    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleModel adminRole = roleService.findByName(RoleNameType.ROLE_ADMIN)
					    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					RoleModel modRole = roleService.findByName(RoleNameType.ROLE_MODERATOR)
					    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					RoleModel userRole = roleService.findByName(RoleNameType.ROLE_USER)
					    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userService.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principle.toString() != "anonymousUser") {      
      UUID userId = ((UserDetailsImpl) principle).getId();
      refreshTokenService.deleteByUserId(userId);
    }
    
    ResponseCookie cookie = tokenProvider.getCleanCookie();
    ResponseCookie RefreshCookie = tokenProvider.getCleanRefreshCookie();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .header(HttpHeaders.SET_COOKIE, RefreshCookie.toString())
        .body(new MessageResponse("You've been signed out!"));		

	}

}