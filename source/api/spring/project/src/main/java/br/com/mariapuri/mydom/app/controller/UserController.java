package br.com.mariapuri.mydom.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mariapuri.mydom.app.domain.dto.UserDTO;
import br.com.mariapuri.mydom.app.service.UserService;
import br.com.mariapuri.mydom.util.modelmapper.UserMapper;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		var users = userMapper.toCollectionDTO(userService.findAll());
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}	
	
}
