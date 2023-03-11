package br.com.mariapuri.mydom.config.security.auth;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import br.com.mariapuri.mydom.app.domain.model.RoleModel;
import br.com.mariapuri.mydom.config.security.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class AuthTokenProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenProvider.class);
	
  @Value("${security.jwt.cookie-name}")
  private String jwtCookieName;

  @Value("${security.jwt.cookie-refresh-name}")
  private String jwtCookieRefreshName;  
  
  @Value("${security.jwt.issuer:jwt-provider}")
  private String issuer;

  @Value("${security.jwt.token.secret-key:secret}")
  private String tokenSecretKey;

  @Value("${security.jwt.audience}")
  private String audience;

  @Value("${security.jwt.token.expire-length-ms:60000}") // 1h
  private long validityInMilliseconds;  

  @Value("${security.jwt.token.refresh-expire-length:180000}") // 2h
  private long validityRefreshInMilliseconds;  
  
  
  private static final String CLAIM_USER_NAME_KEY = "UserName";
  private static final String CLAIM_EMAIL_KEY = "EmailName";
	
    
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@PostConstruct
	protected void init() {
		tokenSecretKey = Base64.getEncoder().encodeToString(tokenSecretKey.getBytes());
	}
	
	public String createToken(String username, Set<RoleModel> roles) {
		return createToken(username,roles, SignatureAlgorithm.HS256);
	}
	
	
  public String createToken(String username, Set<RoleModel> roles, SignatureAlgorithm signatureAlgorithm ) {
    
  	//UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
  	
  	Claims claims = Jwts.claims()
  			.setId(UUID.randomUUID().toString())
  			.setSubject(username);
    
  	claims.put("roles", roles);
  	claims.put(CLAIM_USER_NAME_KEY, "userPrincipal.getUsername()");
  	claims.put(CLAIM_EMAIL_KEY, "userPrincipal.getEmail()");  	
    
  	
		Date now = Date.from(Instant.now());
		Date validity = Date.from(Instant.now().plus(Duration.ofSeconds(validityInMilliseconds)));
    
    return Jwts.builder()
        .setClaims(claims)
        .setIssuer(issuer)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(signatureAlgorithm, tokenSecretKey)
        .compact();
  }
  
	public ResponseCookie createTokenCookie(String username, Set<RoleModel> roles) {
		var token = createToken(username,roles, SignatureAlgorithm.HS512);
		return  ResponseCookie.from(jwtCookieName, token).path("/api").maxAge(validityInMilliseconds).httpOnly(true).build(); //24 * 60 * 60
	} 
	
  public ResponseCookie generateRefreshJwtCookie(String refreshToken) {
    return generateCookie(jwtCookieRefreshName, refreshToken, "/api/auth/refreshtoken");
  }	
  
  public String getJwtRefreshFromCookies(HttpServletRequest request) {
    return getCookieValueByName(request, jwtCookieRefreshName);
  }  
  

	
  
  private String getCookieValueByName(HttpServletRequest request, String name) {
    Cookie cookie = WebUtils.getCookie(request, name);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }  
  
  
  private ResponseCookie generateCookie(String name, String value, String path) {
    ResponseCookie cookie = ResponseCookie.from(name, value).path(path).maxAge(validityInMilliseconds).httpOnly(true).build(); //24 * 60 * 60
    return cookie;
  }  
  
  
  
  
  
  
	 
  public Authentication getAuthentication(String token) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
      return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }
  
	public String getUsername(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}  
  
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}	

	//for retrieveing any information from token we will need the secret key
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(tokenSecretKey).parseClaimsJws(token).getBody();
	}	
	
	//check if the token has expired
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}	
	
	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}	
  
  
  public String resolveToken(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
          return bearerToken.substring(7, bearerToken.length());
      }
      return null;
  }
  
  public String resolveTokenCookie(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookieName);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }
  
  public boolean validateToken(String token) {
  	Date now = Date.from(Instant.now());
  	Date limit = Date.from(Instant.now().plusSeconds(validityInMilliseconds + validityRefreshInMilliseconds));
  	
    try {
    	
    	Jws<Claims> claims = Jwts.parser().setSigningKey(tokenSecretKey).parseClaimsJws(token);
      if (claims.getBody().getExpiration().before(now) || claims.getBody().getExpiration().after(limit)) {
        return false;
      }    	
      return true;
      
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    } catch (JwtException e) {
    	logger.error("JWT Exception: {}", e.getMessage());
    }

    return false;  

  }
  
  public ResponseCookie getCleanCookie() {
    return ResponseCookie.from(jwtCookieName, null).path("/api/auth/").build();
  }   
  
  public ResponseCookie getCleanRefreshCookie() {
  	return ResponseCookie.from(jwtCookieRefreshName, null).path("/api/auth/refreshtoken").build();
  } 
  
  
}

