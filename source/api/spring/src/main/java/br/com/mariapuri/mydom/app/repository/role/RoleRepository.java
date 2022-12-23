package br.com.mariapuri.mydom.app.repository.role;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mariapuri.mydom.app.domain.model.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

}
