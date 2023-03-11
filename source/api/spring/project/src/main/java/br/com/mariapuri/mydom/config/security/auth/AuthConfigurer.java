package br.com.mariapuri.mydom.config.security.auth;

import javax.servlet.Filter;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.mariapuri.mydom.enums.AuthTokenFilterType;

public class AuthConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private AuthTokenProvider tokenProvider;
	private AuthenticationProvider authenticationProvider;
	private AuthTokenFilterType authTokenFilterType;

	public AuthConfigurer(AuthTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
		this.authTokenFilterType = AuthTokenFilterType.FILTER_GENERIC;
	}

	public AuthConfigurer(AuthTokenProvider tokenProvider, AuthenticationProvider authenticationProvider) {
		this.tokenProvider = tokenProvider;
		this.authenticationProvider = authenticationProvider;
		this.authTokenFilterType = AuthTokenFilterType.FILTER_PER_REQUEST;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		Filter filter = null;
		switch (authTokenFilterType) {
		case FILTER_GENERIC:
			filter = new AuthTokenFilterGeneric(tokenProvider);
			break;
		case FILTER_PER_REQUEST:
			http.authenticationProvider(authenticationProvider);
			filter = new AuthTokenFilterPerRequest(tokenProvider);
		}
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

}
