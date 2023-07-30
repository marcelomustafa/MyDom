package br.com.mariapuri.mydom.config.security.payload.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {

	@NotBlank
	private String name;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String email;
  
	@NotBlank
  @Size(min = 3, max = 20)	
	private String username;
	
  @NotBlank
  @Size(min = 6, max = 40)
	private String password;
	
	private Set<String> role;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
		
}
