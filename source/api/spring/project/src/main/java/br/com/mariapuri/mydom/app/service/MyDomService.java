package br.com.mariapuri.mydom.app.service;

import br.com.mariapuri.mydom.app.domain.dto.MyDomDTO;
import br.com.mariapuri.mydom.app.domain.model.MyDom;
import br.com.mariapuri.mydom.app.repository.mydom.MyDomRepository;
import br.com.mariapuri.mydom.app.service.custom.BaseService;
import br.com.mariapuri.mydom.util.modelmapper.MyDomMapperDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MyDomService extends BaseService<MyDom, MyDomRepository> {
	
	private final MyDomMapperDTO myDomMapperDTO;

	public MyDomService(
			MyDomMapperDTO myDomMapperDTO
	) {

		this.myDomMapperDTO = myDomMapperDTO;
	}

	
	public MyDomDTO getMyDom() {

		return myDomMapperDTO.apply(
				new MyDom(
						"MyDom",
						"Apresentação de estudo."
				)
		) ;
	}

	
}
