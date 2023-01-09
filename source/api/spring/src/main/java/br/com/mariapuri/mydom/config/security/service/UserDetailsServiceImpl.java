package br.com.mariapuri.mydom.config.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.service.UserService;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		UserModel userModel = userService.findByUserName(userName)
		    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));

		return new User(userModel.getUserName(), userModel.getPassword(), true, true, true, true,
		    userModel.getAuthorities());
	}
}
