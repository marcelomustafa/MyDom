package br.com.mariapuri.mydom.config.securityCookie.controller;
//package br.com.mariapuri.mydom.config.security.controller;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//import br.com.mariapuri.mydom.config.security.domain.LoginRequest;
//
//@RestController
//@RequestMapping("/api/v1/auth/")
//public class AuthController {
// 
//    @Autowired
//    private AuthenticationManager authenticationManager;
// 
//    @Autowired
//    private JWTUtil jwtUtil;
// 
//    @PostMapping(path = "/token",
//            consumes = MediaType.APPLICATION_JSON_VALUE, 
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getToken(@Valid @RequestBody LoginRequest loginRequest) {
// 
//        UsernamePasswordAuthenticationToken loginCredentials =
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUserName(), loginRequest.getPassword());
//         
//        Authentication authentication = 
//                authenticationManager.authenticate(loginCredentials);
// 
//        Userl user = (User) authentication.getPrincipal();
//        String jwtToken = jwtUtil.createJWT(user);
// 
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
//                .build();
// 
//    }
//}