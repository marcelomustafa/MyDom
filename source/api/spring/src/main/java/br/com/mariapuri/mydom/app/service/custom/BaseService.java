package br.com.mariapuri.mydom.app.service.custom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mariapuri.mydom.exceptionhandler.ThrowExceptionHandler;

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

	public Optional<MODEL> findById(ID Id) {
		var oModel = getRepository().findById(Id);
		if (!oModel.isPresent()) {
			new ThrowExceptionHandler();
		}
		return oModel;
	}

  public MODEL savePerson(MODEL model) {
    return getRepository().save(model); 
  }
  
  public void deletePerson(MODEL person) {
  	getRepository().delete(person);  
  }
  	
}
