package br.com.mariapuri.mydom.util.modelmapper;

import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.domain.dto.MyDomDTO;
import br.com.mariapuri.mydom.app.domain.model.MyDom;
import br.com.mariapuri.mydom.util.modelmapper.custom.SimpleMapper;


@Service
public class MyDomMapper implements SimpleMapper<MyDom, MyDomDTO> {
	
	@Override
	public MyDomDTO toDTO(MyDom item) {
		return modelMapper.map(item, MyDomDTO.class);
	}

	@Override
	public MyDom toModel(MyDomDTO itemDTO) {
		return modelMapper.map(itemDTO, MyDom.class);
	}
	
}
