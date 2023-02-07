package br.com.mariapuri.mydom.app.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.model.RoleModel;
import br.com.mariapuri.mydom.app.repository.role.RoleRepository;
import br.com.mariapuri.mydom.app.service.custom.BaseService;
import br.com.mariapuri.mydom.enums.RoleNameType;

@Service
public class RoleService extends BaseService<RoleModel, UUID, RoleRepository> {
	
	public Optional<RoleModel> findByName(RoleNameType name){
		return repository.findByName(name);
	}

}
