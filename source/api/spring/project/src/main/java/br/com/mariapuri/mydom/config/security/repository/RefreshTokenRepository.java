package br.com.mariapuri.mydom.config.security.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.config.security.payload.RefreshToken;

public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, UUID> {

  Optional<RefreshToken> findByToken(String token);
  
  Optional<RefreshToken> findByUserId(UUID userId);

  @Modifying
  int deleteByUser(UserModel user);	
	
}
