package br.com.mariapuri.mydom.config.security.payload.response;

import java.util.List;
import java.util.UUID;

public class UserInfoResponse {
	
	private UUID id;
	private String username;
	private String email;
	private String token;
	private List<String> roles;
	
	
	public UserInfoResponse(UUID id, String username, String email, String token, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.token = token;
		this.roles = roles;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
