package br.com.mariapuri.mydom.app.repository.person;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mariapuri.mydom.app.domain.model.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, UUID>, PersonRepositoryCustom {

}
