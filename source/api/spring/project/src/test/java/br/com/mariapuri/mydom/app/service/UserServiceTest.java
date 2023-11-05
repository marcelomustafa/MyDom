package br.com.mariapuri.mydom.app.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mariapuri.mydom.app.domain.model.UserModel;

class UserServiceTest {
  @Test
  void testGetUserById() {
      UserService userService = new UserService();

      // Preparação
      UUID userId = UUID.randomUUID();

      // Execução
      Optional<UserModel> user = userService.findById(userId);

      // Verificação
      assertEquals(userId, user.get().getId());
  }	
	
	@Test
	void testFindByUserName() {
		fail("Not yet implemented");
	}

}
