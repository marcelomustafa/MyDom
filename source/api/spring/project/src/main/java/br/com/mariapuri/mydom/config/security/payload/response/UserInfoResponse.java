package br.com.mariapuri.mydom.config.security.payload.response;

import java.util.List;
import java.util.UUID;

public record UserInfoResponse(
		UUID id, 
		String username, 
		String email, 
		String token, 
		List<String> roles) {}
