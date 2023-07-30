package br.com.mariapuri.mydom.config.security.payload.request;

import br.com.mariapuri.mydom.enums.AuthTokenLoginType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;


public record LoginRequest(
		@NotBlank String username,
		@NotBlank String password,
		@Enumerated(EnumType.STRING) AuthTokenLoginType authTokenType) {}
