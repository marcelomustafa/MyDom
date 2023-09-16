package br.com.mariapuri.mydom.app.repository.person;

import java.util.List;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;

public interface PersonRepositoryCustom {
  public List<PersonModel> filter(PersonDTO filter);
  

  
}
