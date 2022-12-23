package br.com.mariapuri.mydom.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.model.RoleModel;
import br.com.mariapuri.mydom.app.repository.role.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository){
		this.roleRepository = roleRepository;
	}
		
	public RoleModel save(RoleModel userModel) {
		return roleRepository.save(userModel);
	}
	
	public List<RoleModel> findAll(){
		return roleRepository.findAll();
	}
	
}
