package br.com.mariapuri.mydom.config.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.mariapuri.mydom.config.security.service.UserDetailsServiceImpl;

//@Configuration
//@EnableWebSecurity /*Disable default configuration Spring Security*/
public class WebSecurtiyConfigOld extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsServiceImpl userDetailsService;
	
	public WebSecurtiyConfigOld(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			
//    .antMatchers(HttpMethod.GET,"/api/**").permitAll()
//    .antMatchers(HttpMethod.GET,"/api/**").hasRole("ADMIN")

//    .antMatchers(HttpMethod.GET,"/parking-spot/**").permitAll()
//    .antMatchers(HttpMethod.POST,"/parking-spot").hasRole("USER")
//  	.antMatchers(HttpMethod.POST,"/parking-spot").hasAnyRole("USER", "ADMIN")
//    .antMatchers(HttpMethod.DELETE,"/parking-spot/**").hasRole("ADMIN")			
			
//  	.anyRequest().permitAll();
		  .anyRequest().authenticated()
		  .and()
		  .csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.inMemoryAuthentication()
//			.withUser("mydom")
//			.password(passwordEncoder().encode("mydompass"))
//			.roles("ADMIN");

		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
			
	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
	    return authenticationManager();
	}		
	
}
