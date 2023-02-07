package br.com.mariapuri.mydom.app.repository.role;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mariapuri.mydom.app.domain.model.RoleModel;
import br.com.mariapuri.mydom.enums.RoleNameType;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
	
	public Optional<RoleModel> findByName(RoleNameType name);

}
