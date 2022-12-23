package br.com.mariapuri.mydom.app.service;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.dto.MyDomDTO;
import br.com.mariapuri.mydom.app.repository.mydom.MyDomRepository;

//@AllArgsConstructor
@Service
public class MyDomService {
	
	private final MyDomRepository myDomRepository;
	
	public MyDomService(MyDomRepository myDomRepository){
		this.myDomRepository = myDomRepository;
	}
	
	
	public MyDomDTO getMyDom() {
		MyDomDTO appMyDomDto = new MyDomDTO();
		appMyDomDto.setName("MyDom");
		appMyDomDto.setDescription("Apresentação de estudo.");
		return appMyDomDto;
	}

}
