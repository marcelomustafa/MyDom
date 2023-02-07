package br.com.mariapuri.mydom.config.security.jwtAuth;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	//@Autowired
	private AuthTokenProvider tokenProvider;
	public AuthConfigurer(AuthTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
  
  @Override
  public void configure(HttpSecurity http) throws Exception {
      AuthTokenFilter customFilter = new AuthTokenFilter(tokenProvider);
      http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
  
}
