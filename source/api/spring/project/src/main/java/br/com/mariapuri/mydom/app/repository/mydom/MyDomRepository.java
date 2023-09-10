package br.com.mariapuri.mydom.app.repository.mydom;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mariapuri.mydom.app.domain.model.MyDom;

public interface MyDomRepository extends JpaRepository<MyDom, UUID> {

}
