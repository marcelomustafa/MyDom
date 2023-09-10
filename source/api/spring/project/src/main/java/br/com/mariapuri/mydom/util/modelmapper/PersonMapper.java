package br.com.mariapuri.mydom.util.modelmapper;

import org.springframework.stereotype.Component;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;
import br.com.mariapuri.mydom.util.modelmapper.custom.SimpleMapper;

@Component
public class PersonMapper implements SimpleMapper<PersonModel, PersonDTO> {

	@Override
	public PersonDTO toDTO(PersonModel item) {
		return modelMapper.map(item, PersonDTO.class);
	}

	@Override
	public PersonModel toModel(PersonDTO itemDTO) {
		return modelMapper.map(itemDTO, PersonModel.class);
	}

}
