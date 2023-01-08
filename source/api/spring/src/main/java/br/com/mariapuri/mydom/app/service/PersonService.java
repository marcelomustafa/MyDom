package br.com.mariapuri.mydom.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;
import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.repository.person.PersonRepository;
import br.com.mariapuri.mydom.exceptionhandler.ThrowExceptionHandler;
import br.com.mariapuri.mydom.reports.PersonReport;

//@AllArgsConstructor
@Service
public class PersonService {
  
	@Autowired
  private PersonRepository personRepository;


  public List<PersonModel> findAll() {
    return personRepository.findAll();
  }

  public Optional<PersonModel> findById(UUID Id) {
    var person = personRepository.findById(Id);
    if (!person.isPresent()) {
      new ThrowExceptionHandler();
    }
    return person;
  }
  
  public List<PersonModel> filter(PersonDTO filter) {
    //return Arrays.asList(new PersonModel()); 
    return personRepository.filter(filter);
  } 
  
  public PersonModel savePerson(PersonModel person) {
    return personRepository.save(person); 
  }
  
  public void deletePerson(PersonModel person) {
    personRepository.delete(person);  
  }
  
  public byte[] getReportPerson(List<PersonDTO> list, PersonDTO filter) throws Exception {
    var user = new UserModel();
    var report = new PersonReport(list, filter, user);
    return report.getReport();
  }
  
  public byte[] getSheetPerson(List<PersonDTO> list, PersonDTO filter) throws Exception {
    var user = new UserModel();
    var report = new PersonReport(list, filter, user);
    return report.getSheet();    
  } 
  
}
