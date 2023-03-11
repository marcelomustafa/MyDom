package br.com.mariapuri.mydom.app.repository.mydom;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mariapuri.mydom.app.domain.model.MyDomModel;

@Repository
public interface MyDomRepository extends JpaRepository<MyDomModel, UUID> {

}
