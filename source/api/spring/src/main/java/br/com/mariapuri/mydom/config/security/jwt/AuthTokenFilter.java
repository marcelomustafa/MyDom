package br.com.mariapuri.mydom.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


public class AuthTokenFilter extends GenericFilterBean {
  
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	//@Autowired
	private AuthTokenProvider tokenProvider;
	public AuthTokenFilter(AuthTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
  
  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
      throws IOException, ServletException {
      String token = tokenProvider.resolveToken((HttpServletRequest) req);
      if (token != null && tokenProvider.validateToken(token)) {
          Authentication auth = token != null ? tokenProvider.getAuthentication(token) : null;
          SecurityContextHolder.getContext().setAuthentication(auth);
      }
      filterChain.doFilter(req, res);
  }

}
