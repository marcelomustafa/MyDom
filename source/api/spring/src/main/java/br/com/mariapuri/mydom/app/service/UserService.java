package br.com.mariapuri.mydom.app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.repository.user.UserRepository;


@Service
public class UserService {
		
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
		
	public UserModel save(UserModel userModel) {
		return userRepository.save(userModel);
	}
	
	public List<UserModel> findAll(){
		return userRepository.findAll();
	}
	
	public Optional<UserModel> findByUserName(String userName){
		return  userRepository.findByUserName (userName);
	}
	
}
