package br.com.mariapuri.mydom.app.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.dto.UserDTO;
import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.repository.user.UserRepository;
import br.com.mariapuri.mydom.app.service.custom.BaseServiceWithStandartReport;


@Service
public class UserService extends BaseServiceWithStandartReport<UserModel, UserRepository, UserDTO, UserDTO> {
	
	public Optional<UserModel> findByUserName(String userName){
		return  repository.findByUserName (userName);
	}
 		
	public Optional<UserModel> findByEmail(String userName){
		return repository.findByPersonEmail(userName);
	}
	
	public Boolean existsByUserName(String userName) {
		return repository.existsByUserName(userName);
	}
	
	public Boolean existsByEmail(String email) {
		return repository.existsByPersonEmail(email);
	}
	
	
}
