package br.com.mariapuri.mydom.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.mariapuri.mydom.config.security.jwtAuth.AuthConfigurer;
import br.com.mariapuri.mydom.config.security.jwtAuth.AuthEntryPoint;
import br.com.mariapuri.mydom.config.security.jwtAuth.AuthTokenProvider;

@Configuration
//@EnableWebSecurity /*Disable default configuration Spring Security*/
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurtiyConfig {
	
  @Autowired
  private AuthTokenProvider tokenProvider;
  
  @Autowired
  private AuthEntryPoint unauthorizedHandler;  
  
  
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	}	  
  
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
		.httpBasic().and().cors().and().csrf().disable()
		//.httpBasic().and().cors().and().csrf().disable()
		//.formLogin().disable()
    
    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
    .and()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    
    .and()
        .authorizeRequests()
        
        //.antMatchers("/api/auth/**").permitAll()
        
        //.antMatchers("/api/auth/**").permitAll()
        //.antMatchers("/persons/**").permitAll()
        //.antMatchers("/me").permitAll()
        
        .antMatchers("/api/auth/signin").permitAll()
        //.antMatchers("/actuator/**").permitAll()
        //.antMatchers("/api/v1/auth/token").permitAll()
        //.antMatchers("/authenticate").permitAll()
        
        .antMatchers(HttpMethod.POST).hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
        .antMatchers(HttpMethod.GET).hasRole("ADMIN")        
        
        //.antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
        //.antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
        //.antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
        
        
    .anyRequest().authenticated()
		
    .and()
    .apply(new AuthConfigurer(tokenProvider));	
//@formatter:on
		
		return http.build();
		
	}

}


