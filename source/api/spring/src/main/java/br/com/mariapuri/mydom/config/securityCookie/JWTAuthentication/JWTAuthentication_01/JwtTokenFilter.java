package br.com.mariapuri.mydom.config.securityCookie.JWTAuthentication.JWTAuthentication_01;

//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//public class JwtTokenFilter extends GenericFilterBean {
//  
//	private AuthTokenProvider authTokenProvider;
//	
//  public JwtTokenFilter(AuthTokenProvider authTokenProvider) {
//      this.authTokenProvider = authTokenProvider;
//  }
//  
//  @Override
//  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
//      throws IOException, ServletException {
//      String token = authTokenProvider.resolveToken((HttpServletRequest) req);
//      if (token != null && authTokenProvider.validateToken(token)) {
//          Authentication auth = token != null ? authTokenProvider.getAuthentication(token) : null;
//          SecurityContextHolder.getContext().setAuthentication(auth);
//      }
//      filterChain.doFilter(req, res);
//  }
//}