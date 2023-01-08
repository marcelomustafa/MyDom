package br.com.mariapuri.mydom.component.modelmapper;

import org.springframework.stereotype.Component;

import br.com.mariapuri.mydom.app.domain.dto.MyDomDTO;
import br.com.mariapuri.mydom.app.domain.model.MyDomModel;
import br.com.mariapuri.mydom.component.modelmapper.custom.BaseMapper;


@Component
public class MyDomMapper extends BaseMapper<MyDomModel, MyDomDTO> {

	@Override
	public MyDomDTO toDTO(MyDomModel myDomModel) {
		return modelMapper.map(myDomModel, MyDomDTO.class);
	}

	@Override
	public MyDomModel toModel(MyDomDTO myDomDTO) {
		return modelMapper.map(myDomDTO, MyDomModel.class);
	}

}
