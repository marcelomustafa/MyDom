package br.com.mariapuri.mydom.app.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mariapuri.mydom.app.domain.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
	
	Optional<UserModel> findByUserName(String userName);
		
}

