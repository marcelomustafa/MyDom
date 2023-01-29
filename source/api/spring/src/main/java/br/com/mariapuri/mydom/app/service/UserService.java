package br.com.mariapuri.mydom.app.service;


import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.dto.UserDTO;
import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.repository.user.UserRepository;
import br.com.mariapuri.mydom.app.service.custom.BaseServiceWithStandartReport;


@Service
public class UserService extends BaseServiceWithStandartReport<UserModel, UUID, UserRepository, UserDTO, UserDTO> {
	
	public Optional<UserModel> findByUserName(String userName){
		return  repository.findByUserName (userName);
	}
 		
	
}
