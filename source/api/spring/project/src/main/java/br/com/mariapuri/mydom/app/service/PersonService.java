package br.com.mariapuri.mydom.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;
import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.repository.person.PersonRepository;
import br.com.mariapuri.mydom.app.service.custom.BaseService;
import br.com.mariapuri.mydom.reports.PersonReport;

//@AllArgsConstructor
@Service
public class PersonService extends BaseService<PersonModel, PersonRepository> {
  
  public List<PersonModel> filter(PersonDTO filter) {
    //return Arrays.asList(new PersonModel()); 
    return repository.filter(filter);
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
