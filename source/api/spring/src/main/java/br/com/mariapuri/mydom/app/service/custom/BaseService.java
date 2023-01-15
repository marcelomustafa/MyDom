package br.com.mariapuri.mydom.app.service.custom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<MODEL, ID, REPOSITORY> {

	@Autowired
	protected REPOSITORY repository;

	@SuppressWarnings("unchecked")
	private JpaRepository<MODEL, ID> getRepository() {
		return (JpaRepository<MODEL, ID>) repository;
	}

	public List<MODEL> findAll() {
		return getRepository().findAll();
	}

	public Optional<MODEL> findById(ID id) {
		return getRepository().findById(id);
	}

  public MODEL save(MODEL model) {
    return getRepository().save(model); 
  }
  
  public void delete(MODEL model) {
  	getRepository().delete(model);  
  }
  	
  public void deleteById(ID id) {
  	getRepository().deleteById(id);
  }
  
}
