package br.com.mariapuri.mydom.app.service.custom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<M, R> {

	@Autowired
  protected R repository;


	@SuppressWarnings("unchecked")
	private JpaRepository<M, UUID> getRepository() {
		try {
			return (JpaRepository<M, UUID>) repository;
		} catch (Exception e) {
			throw new RuntimeException("A repository type cast failure occurred.");
		}
	}

	public List<M> findAll() {
		return getRepository().findAll();
	}

	public Optional<M> findById(UUID id) {
		return getRepository().findById(id);
	}

  public M save(M model) {
    return getRepository().save(model); 
  }
  
  public void delete(M model) {
  	getRepository().delete(model);  
  }
  	
  public void deleteById(UUID id) {
  	getRepository().deleteById(id);
  }
  
}
