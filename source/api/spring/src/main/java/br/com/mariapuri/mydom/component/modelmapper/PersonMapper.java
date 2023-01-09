package br.com.mariapuri.mydom.component.modelmapper;

import org.springframework.stereotype.Component;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;
import br.com.mariapuri.mydom.component.modelmapper.custom.BaseMapper;

@Component
public class PersonMapper extends BaseMapper<PersonModel, PersonDTO> {

	@Override
	public PersonDTO toDTO(PersonModel item) {
		return modelMapper.map(item, PersonDTO.class);
	}

	@Override
	public PersonModel toModel(PersonDTO itemDTO) {
		return modelMapper.map(itemDTO, PersonModel.class);
	}

}
