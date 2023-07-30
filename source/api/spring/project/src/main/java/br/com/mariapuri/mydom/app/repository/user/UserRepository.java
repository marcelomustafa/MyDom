package br.com.mariapuri.mydom.app.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mariapuri.mydom.app.domain.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
	
	Optional<UserModel> findByUserName(String userName);
	Optional<UserModel> findByPersonEmail(String email);
	Boolean existsByUserName(String userName);
	Boolean existsByPersonEmail(String email);
		
}

