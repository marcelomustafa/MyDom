package br.com.mariapuri.mydom.app.repository.person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;
import br.com.mariapuri.mydom.component.tools.ToolUtils;

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

}
