package br.com.mariapuri.mydom.config.security.auth;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilterPerRequest extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilterPerRequest.class);

	private AuthTokenProvider tokenProvider;

	public AuthTokenFilterPerRequest(AuthTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {

		try {
			String token = tokenProvider.resolveTokenCookie((HttpServletRequest) request);
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
