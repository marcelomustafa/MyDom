package br.com.mariapuri.mydom.app.domain.dto;

import java.util.UUID;

import br.com.mariapuri.mydom.enums.RoleNameType;

public class RoleDTO {

	private UUID id;
	private RoleNameType name;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public RoleNameType getName() {
		return name;
	}
	public void setName(RoleNameType name) {
		this.name = name;
	}
	
	
}
