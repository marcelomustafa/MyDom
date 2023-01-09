package br.com.mariapuri.mydom.app.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.model.RoleModel;
import br.com.mariapuri.mydom.app.repository.role.RoleRepository;
import br.com.mariapuri.mydom.app.service.custom.BaseService;

@Service
public class RoleService extends BaseService<RoleModel, UUID, RoleRepository> {

}
