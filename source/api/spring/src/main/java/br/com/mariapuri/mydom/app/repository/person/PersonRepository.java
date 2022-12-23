package br.com.mariapuri.mydom.app.repository.person;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mariapuri.mydom.app.domain.model.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, UUID>, PersonRepositoryCustom {

}
