package br.com.mariapuri.mydom.app.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.repository.user.UserRepository;
import br.com.mariapuri.mydom.exceptionhandler.ThrowExceptionHandler;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	
	public List<UserModel> findAll(){
		return userRepository.findAll();
	}	
	
  public Optional<UserModel> findById(UUID userId) {
    var user = userRepository.findById(userId);
    if (!user.isPresent()) {
      new ThrowExceptionHandler();
    }
    return user;
  }	
	
	public Optional<UserModel> findByUserName(String userName){
		return  userRepository.findByUserName (userName);
	}
  		
	public UserModel saveUser(UserModel user) {
		return userRepository.save(user);
	}
	
  public void deleteUser(UserModel user) {
  	userRepository.delete(user);  
  }	
	
}
