package br.com.mariapuri.mydom.config.security.payload.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest (
		@NotBlank String name,
		@NotBlank String documento,
		@NotBlank String email,
		@NotBlank @Size(min = 3, max = 20) String username,
		@NotBlank @Size(min = 6, max = 40) String password,
		Set<String> role) {}
