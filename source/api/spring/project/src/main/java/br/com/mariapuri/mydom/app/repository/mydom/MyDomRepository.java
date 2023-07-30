package br.com.mariapuri.mydom.app.repository.mydom;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mariapuri.mydom.app.domain.model.MyDomModel;

public interface MyDomRepository extends JpaRepository<MyDomModel, UUID> {

}
