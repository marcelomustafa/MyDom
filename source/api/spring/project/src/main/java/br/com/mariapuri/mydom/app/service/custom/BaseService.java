package br.com.mariapuri.mydom.app.service.custom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<MODEL, REPOSITORY> {

	@Autowired
	protected REPOSITORY repository;

	@SuppressWarnings("unchecked")
	private JpaRepository<MODEL, UUID> getRepository() {
		return (JpaRepository<MODEL, UUID>) repository;
	}

	public List<MODEL> findAll() {
		return getRepository().findAll();
	}

	public Optional<MODEL> findById(UUID id) {
		return getRepository().findById(id);
	}

  public MODEL save(MODEL model) {
    return getRepository().save(model); 
  }
  
  public void delete(MODEL model) {
  	getRepository().delete(model);  
  }
  	
  public void deleteById(UUID id) {
  	getRepository().deleteById(id);
  }
  
}
