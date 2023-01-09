package br.com.mariapuri.mydom.app.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.dto.MyDomDTO;
import br.com.mariapuri.mydom.app.domain.model.MyDomModel;
import br.com.mariapuri.mydom.app.repository.mydom.MyDomRepository;
import br.com.mariapuri.mydom.app.service.custom.BaseService;

//@AllArgsConstructor
@Service
public class MyDomService extends BaseService<MyDomModel, UUID, MyDomRepository> {
	
	public MyDomDTO getMyDom() {
		MyDomDTO appMyDomDto = new MyDomDTO();
		appMyDomDto.setName("MyDom");
		appMyDomDto.setDescription("Apresentação de estudo.");
		return appMyDomDto;
	
	}

}
