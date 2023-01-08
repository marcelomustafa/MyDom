package br.com.mariapuri.mydom.config.security.config;

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

import br.com.mariapuri.mydom.config.security.JWTAuthentication.JwtConfigurer;
import br.com.mariapuri.mydom.config.security.JWTAuthentication.JwtTokenProvider;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurtiyConfig {
	
  @Autowired 
  JwtTokenProvider jwtTokenProvider;
  
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}		

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http
//		.httpBasic()
//		.and()
//		.authorizeHttpRequests()
//		
////  .antMatchers(HttpMethod.GET,"/api/**").permitAll()
////  .antMatchers(HttpMethod.GET,"/api/**").hasRole("ADMIN")
//
////  .antMatchers(HttpMethod.GET,"/parking-spot/**").permitAll()
////  .antMatchers(HttpMethod.POST,"/parking-spot").hasRole("USER")
////	.antMatchers(HttpMethod.POST,"/parking-spot").hasAnyRole("USER", "ADMIN")
////  .antMatchers(HttpMethod.DELETE,"/parking-spot/**").hasRole("ADMIN")			
//		
////	.anyRequest().permitAll();
//	  .anyRequest().authenticated()
//	  .and()
//	  .csrf().disable();
		
		
//    http
//    .formLogin().disable()
//    .csrf().disable()
//    .httpBasic()
//    .and()
//    .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//    .and()
//    .authorizeRequests()
//    .antMatchers("/actuator/**").permitAll()
//    .antMatchers("/api/v1/auth/token").permitAll()
//    .antMatchers("/authenticate").permitAll()
//    .antMatchers(HttpMethod.POST).hasRole("ADMIN")
//    .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
//    .antMatchers(HttpMethod.GET).hasRole("ADMIN")
//    .anyRequest().authenticated();		
		

		http
    .httpBasic().disable()
    .csrf().disable()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
        .authorizeRequests()
        
        .antMatchers("/auth/signin").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .antMatchers("/api/v1/auth/token").permitAll()
        .antMatchers("/authenticate").permitAll()
        
        .antMatchers(HttpMethod.POST).hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
        .antMatchers(HttpMethod.GET).hasRole("ADMIN")        
        
//      .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
//      .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
//      .antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
        
        .anyRequest().authenticated()
    .and()
    .apply(new JwtConfigurer(jwtTokenProvider));
//@formatter:on
		
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
