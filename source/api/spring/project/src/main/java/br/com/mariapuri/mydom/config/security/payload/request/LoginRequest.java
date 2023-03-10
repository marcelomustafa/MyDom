package br.com.mariapuri.mydom.config.security.payload.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import br.com.mariapuri.mydom.enums.AuthTokenLoginType;

public class LoginRequest {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	//@NotBlank
	@Enumerated(EnumType.STRING)
	private AuthTokenLoginType authTokenType;
	
//	//@NotBlank
//	private Integer authTokenType;	


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Integer getAuthTokenType() {
//		return authTokenType;
//	}
//
//	public void setAuthTokenType(Integer authTokenType) {
//		this.authTokenType = authTokenType;
//	}

	public AuthTokenLoginType getAuthTokenType() {
		return authTokenType;
	}

	public void setAuthTokenType(AuthTokenLoginType authTokenType) {
		this.authTokenType = authTokenType;
	}	
	
	

}
