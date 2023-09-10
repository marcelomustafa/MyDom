package br.com.mariapuri.mydom.app.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record MyDomDTO(
		
		@NotBlank String name,
		String description		
		
){
	
	public MyDomDTO(String name) {
		this(name, null);
	}
	
}

