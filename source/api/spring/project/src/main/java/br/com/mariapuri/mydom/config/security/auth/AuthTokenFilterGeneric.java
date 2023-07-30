package br.com.mariapuri.mydom.config.security.auth;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AuthTokenFilterGeneric extends GenericFilterBean {

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilterGeneric.class);

	private AuthTokenProvider tokenProvider;

	public AuthTokenFilterGeneric(AuthTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
	    throws IOException, ServletException {

		try {
			String token = tokenProvider.resolveToken((HttpServletRequest) request);
			if (token != null && tokenProvider.validateToken(token)) {
				Authentication auth = token != null ? tokenProvider.getAuthentication(token) : null;
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);

	}

}
