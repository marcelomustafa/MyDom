package br.com.mariapuri.mydom.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.service.UserService;

//@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService){
		this.userService = userService;	
	}
	
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping
    public ResponseEntity<List<UserModel>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }	
		

}
