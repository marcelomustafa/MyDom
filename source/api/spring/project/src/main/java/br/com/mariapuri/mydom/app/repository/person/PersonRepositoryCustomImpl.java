package br.com.mariapuri.mydom.app.repository.person;

import java.util.ArrayList;
import java.util.List;

import br.com.mariapuri.mydom.app.domain.model.PersonModel_;
import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.domain.model.UserModel_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;
import br.com.mariapuri.mydom.util.ToolUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import javax.persistence.criteria.Path;

//@AllArgsConstructor
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom{
  
  @PersistenceContext
  private EntityManager eManager;

  @Autowired
  private ToolUtils toolUtils;

  
  @Override
  public List<PersonModel> filter(PersonDTO filter) {
    
    CriteriaBuilder builder = eManager.getCriteriaBuilder();
    CriteriaQuery<PersonModel> query = builder.createQuery(PersonModel.class);
    Root<PersonModel> person = query.from(PersonModel.class);
    
    
    List<Predicate> predicates = new ArrayList<>();
    
    if(filter.getId() != null && StringUtils.hasText(filter.getId().toString()))
      predicates.add(builder.equal(person.get("id"), filter.getId()));
    
    if(filter.getDocumento() != null && StringUtils.hasText(filter.getDocumento())) {
      var documento = filter.getDocumento().replace("[^0-9]", "");
      predicates.add(builder.equal(person.get("documento"), documento));
    }
    
    if(filter.getName() != null && StringUtils.hasText(filter.getName())) {
      var name = toolUtils.accentLass(filter.getName());
      predicates.add(builder.like(builder.function("f_upper_noaccent", String.class, builder.upper(person.get("name"))), "%" + name.toUpperCase() + "%" ));
    }
    
    
    query.select(person)
      .distinct(false)
      .where(predicates.toArray(new Predicate[]{}));
    
    
    TypedQuery<PersonModel> tResult = eManager.createQuery(query);
    return tResult.getResultList();
    
  }

  private void teste(){

    CriteriaBuilder builder = eManager.getCriteriaBuilder();
    CriteriaQuery<UserModel> query = builder.createQuery(UserModel.class);
    Root<UserModel> user = query.from(UserModel.class);


//    Path idPerson = PersonModel.id;
//    join<UserModel, PersonModel> person = user.join(idPerson, UserModel_.person. );

    var r = eManager.createQuery(query);

  }

}
