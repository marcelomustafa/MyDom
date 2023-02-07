package br.com.mariapuri.mydom.config.security.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mariapuri.mydom.app.service.RoleService;
import br.com.mariapuri.mydom.app.service.UserService;
import br.com.mariapuri.mydom.config.security.jwt.AuthTokenProvider;
import br.com.mariapuri.mydom.config.security.payload.request.LoginRequest;

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
	PasswordEncoder encoder;

	@Autowired
	AuthTokenProvider tokenProvider;
	
	
  @GetMapping("/me")
  public ResponseEntity<?> currentUser(@AuthenticationPrincipal UserDetails userDetails){
      Map<Object, Object> model = new HashMap<>();
      model.put("username", userDetails.getUsername());
      model.put("roles", userDetails.getAuthorities()
          .stream()
          .map(a -> ((GrantedAuthority) a).getAuthority())
          .collect(Collectors.toList())
      );
      return ResponseEntity.status(HttpStatus.OK).body(model);
  }	
	

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest data) {

		try {
			String username = data.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = tokenProvider.createToken(username, this.userService.findByUserName(username)
			    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ResponseEntity.status(HttpStatus.OK).body(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}

	}

//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//		try {
//			Authentication authentication = authenticationManager.authenticate(
//			    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//
//			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//			ResponseCookie jwtCookie = tokenProvider.generateJwtCookie(userDetails);
//
//			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//			    .collect(Collectors.toList());
//
//			// String token = tokenProvider.createToken(loginRequest.getUsername(),
//			// this.userService.findByUserName(loginRequest.getUsername()).orElseThrow(() ->
//			// new UsernameNotFoundException("Username " + loginRequest.getUsername() + "not
//			// found")).getRoles());
//
//			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
//			    new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), "", roles));
//
//		} catch (AuthenticationException e) {
//			throw new BadCredentialsException("Invalid username/password supplied");
//		}
//	}

//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//		if (userService.existsByUserName(signUpRequest.getUsername())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//		}
//
//		if (userService.existsByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//		}
//
//		// Create new user's account
//		UserModel user = new UserModel(signUpRequest.getName(), signUpRequest.getDocumento(), signUpRequest.getEmail(),
//		    signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
//
//		Set<String> strRoles = signUpRequest.getRole();
//		Set<RoleModel> roles = new HashSet<>();
//
//		if (strRoles == null) {
//			RoleModel userRole = roleService.findByName(RoleNameType.ROLE_USER)
//			    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "admin":
//					RoleModel adminRole = roleService.findByName(RoleNameType.ROLE_ADMIN)
//					    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				case "mod":
//					RoleModel modRole = roleService.findByName(RoleNameType.ROLE_MODERATOR)
//					    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
//				default:
//					RoleModel userRole = roleService.findByName(RoleNameType.ROLE_USER)
//					    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}
//
//		user.setRoles(roles);
//		userService.save(user);
//
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	}

//	@PostMapping("/signout")
//	public ResponseEntity<?> logoutUser() {
//		ResponseCookie cookie = tokenProvider.getCleanJwtCookie();
//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//		    .body(new MessageResponse("You've been signed out!"));
//	}

}