package br.com.mariapuri.mydom.config.securityCookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.mariapuri.mydom.config.security.jwtAuth.AuthEntryPoint;
import br.com.mariapuri.mydom.config.security.service.UserDetailsServiceImpl;
import br.com.mariapuri.mydom.config.securityCookie.jwt.AuthTokenFilter;

//@Configuration
////@EnableWebSecurity /*Disable default configuration Spring Security*/
//@EnableGlobalMethodSecurity(
//    // securedEnabled = true,
//    // jsr250Enabled = true,
//    prePostEnabled = true)
public class WebSecurtiyConfig {
	
  @Autowired
  UserDetailsServiceImpl userDetailsService;
  
  @Autowired
  private AuthEntryPoint unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }  
  
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }  
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }  
  
  
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
		.cors().and().csrf().disable()
		//.httpBasic().and().cors().and().csrf().disable()
    //.httpBasic().disable()
		//.formLogin().disable()
    
    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
    .and()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    
    .and()
        .authorizeRequests()
        
        //.antMatchers("/api/auth/**").permitAll()
        
        .antMatchers("/api/auth/**").permitAll()
        //.antMatchers("/persons/**").permitAll()
        //.antMatchers("/me").permitAll()
        
        //.antMatchers("/api/auth/signin").permitAll()
        //.antMatchers("/actuator/**").permitAll()
        //.antMatchers("/api/v1/auth/token").permitAll()
        //.antMatchers("/authenticate").permitAll()
        
        .antMatchers(HttpMethod.POST).hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
        .antMatchers(HttpMethod.GET).hasRole("ADMIN")        
        
        //.antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
        //.antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
        //.antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
        
        
    .anyRequest().authenticated();

		
    http.authenticationProvider(authenticationProvider());
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		

		
		return http.build();
		
	}

}


