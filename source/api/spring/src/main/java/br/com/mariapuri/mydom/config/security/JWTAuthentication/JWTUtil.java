//package br.com.mariapuri.mydom.config.security.JWTAuthentication;
//
//import java.io.UnsupportedEncodingException;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Date;
//import java.util.UUID;
//
//import javax.annotation.PostConstruct;
//import javax.crypto.SecretKey;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
////import com.dev.springdemo.auth.user.User;
//
//import br.com.mariapuri.mydom.app.domain.model.UserModel;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.log4j.Log4j2;
//
//
////@Component
////@Log4j2
//public class JWTUtil {
//	
//    @Value("${auth.jwt.issuer}")
//    private String issuer;
// 
//    @Value("${auth.jwt.secret}")
//    private String secret;
// 
//    @Value("${auth.jwt.audience}")
//    private String audience;
// 
//    @Value("${auth.jwt.ttl-in-seconds}")
//    private long timeToLiveInSeconds;
//    
//    
//    /****/
//    private SecretKey secretKey;
//    
//    @PostConstruct
//    public void setUpSecretKey() {
//        try {
//            secretKey = Keys.hmacShaKeyFor(secret.getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            log.error("Error generating JWT Secret Key : {}", e.getMessage());
//            throw new RuntimeException("Error generating JWT Secret Key", e);
//        }
//    }    
//    
//    /****/
//    private static final String CLAIM_FIRST_NAME_KEY = "FirstName";
//    private static final String CLAIM_LAST_NAME_KEY = "LastName";
//     
//     
//    public String createJWT(UserModel user) {
//     
//        String jwt =
//            Jwts.builder()
//                .setId(UUID.randomUUID().toString())
//                .setSubject(user.getUsername())
//                .setIssuer(issuer)
//                .setIssuedAt(Date.from(Instant.now()))
//                .setExpiration(Date.from(Instant.now().plus(
//                      Duration.ofSeconds(timeToLiveInSeconds))))
////                .claim(CLAIM_FIRST_NAME_KEY, user.getFirstName())
////                .claim(CLAIM_LAST_NAME_KEY, user.getLastName())
//                .claim(CLAIM_FIRST_NAME_KEY, user.getUsername())
//                .claim(CLAIM_LAST_NAME_KEY, user.getUsername())
//                .signWith(secretKey)
//                .compact();
//        return jwt;
//    } 
//    
//    
//    /****/
//    public Claims parseJWT(String jwtString) {
//    	 
//      Jws<Claims> headerClaimsJwt =
//          Jwts.parserBuilder()
//              .setSigningKey(secretKey)
//              .build()
//              .parseClaimsJws(jwtString);
//   
//      Claims claims = headerClaimsJwt.getBody();
//   
//      return claims;
//  }
//}