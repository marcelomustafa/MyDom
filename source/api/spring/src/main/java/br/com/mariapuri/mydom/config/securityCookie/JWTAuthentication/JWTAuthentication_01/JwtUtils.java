package br.com.mariapuri.mydom.config.securityCookie.JWTAuthentication.JWTAuthentication_01;

//import java.time.Duration;
//import java.time.Instant;
//import java.util.Date;
//import java.util.UUID;
//import java.util.function.Function;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseCookie;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.WebUtils;
//
//import br.com.mariapuri.mydom.config.security.domain.UserSecurity;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//
//
//@Component
//public class JwtUtils {
//  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//
//  @Value("${auth.jwt.cookie-name}")
//  private String jwtCookie;
//  
//  @Value("${auth.jwt.issuer}")
//  private String issuer;
//
//  @Value("${auth.jwt.secret}")
//  private String jwtSecret;
//
//  @Value("${auth.jwt.audience}")
//  private String audience;
//
//  @Value("${auth.jwt.ttl-in-seconds}")
//  private long jwtExpiration;  
//  
//  private static final String CLAIM_USER_NAME_KEY = "UserName";
//  private static final String CLAIM_EMAIL_KEY = "EmailName";  
//  
//  
//  
//  public String getUserNameFromJwtToken(String authToken) {
//    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody().getSubject();
//  }
//  
//  public String generateJwtToken(Authentication authentication) {
//
//    UserSecurity userPrincipal = (UserSecurity) authentication.getPrincipal();
//
//    return Jwts.builder()
//    		.setId(UUID.randomUUID().toString())
//        .setSubject((userPrincipal.getUsername()))
//        
//        .setIssuer(issuer)
//        .setIssuedAt(Date.from(Instant.now()))
//        //.setIssuedAt(new Date())
//        
//        
//        .setExpiration(Date.from(Instant.now().plus(Duration.ofSeconds(jwtExpiration))))
//        //.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                
//        .claim(CLAIM_USER_NAME_KEY, userPrincipal.getUsername())
//        .claim(CLAIM_EMAIL_KEY, userPrincipal.getEmail())        
//        
//        .signWith(SignatureAlgorithm.HS512, jwtSecret)
//        .compact();
//  } 
//  
//  public boolean validateJwtToken(String authToken) {
//    try {
//      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//      return true;
//    } catch (SignatureException e) {
//      logger.error("Invalid JWT signature: {}", e.getMessage());
//    } catch (MalformedJwtException e) {
//      logger.error("Invalid JWT token: {}", e.getMessage());
//    } catch (ExpiredJwtException e) {
//      logger.error("JWT token is expired: {}", e.getMessage());
//    } catch (UnsupportedJwtException e) {
//      logger.error("JWT token is unsupported: {}", e.getMessage());
//    } catch (IllegalArgumentException e) {
//      logger.error("JWT claims string is empty: {}", e.getMessage());
//    }
//
//    return false;
//  }  
//
//  public ResponseCookie getCleanJwtCookie() {
//    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
//    return cookie;
//  }
//
//  public String getJwtFromCookies(HttpServletRequest request) {
//    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
//    if (cookie != null) {
//      return cookie.getValue();
//    } else {
//      return null;
//    }
//  }
//	
//	//retrieve username from jwt token
//	public String getUsernameFromToken(String authToken) {
//		return getClaimFromToken(authToken, Claims::getSubject);
//	}
//
//	//retrieve expiration date from jwt token
//	public Date getExpirationDateFromToken(String authToken) {
//		return getClaimFromToken(authToken, Claims::getExpiration);
//	}
//
//	public <T> T getClaimFromToken(String authToken, Function<Claims, T> claimsResolver) {
//		final Claims claims = getAllClaimsFromToken(authToken);
//		return claimsResolver.apply(claims);
//	}
//  
//	//for retrieveing any information from token we will need the secret key
//	public Claims getAllClaimsFromToken(String authToken) {
//		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody();
//	}
//
//	//check if the token has expired
//	public Boolean isTokenExpired(String authToken) {
//		final Date expiration = getExpirationDateFromToken(authToken);
//		return expiration.before(new Date());
//	}
//
//
//	
//	
//
////  public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
////    String jwt = generateTokenFromUsername(userPrincipal.getUsername());
////    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
////    return cookie;
////  }  
//  
////  public String generateTokenFromUsername(String username) {   
////    return Jwts.builder()
////        .setSubject(username)
////        .setIssuedAt(new Date())
////        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
////        .signWith(SignatureAlgorithm.HS512, jwtSecret)
////        .compact();
////  }
//	
////@PostConstruct
////public void setUpSecretKey() {
////	try {
////		secretKey =  Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));
////	} catch (UnsupportedEncodingException e) {
////		logger.error("Error generating JWT Secret Key : {}", e.getMessage());
////		throw new RuntimeException("Error generating JWT Secret Key", e);
////	}
////}   
////	
//  
//}