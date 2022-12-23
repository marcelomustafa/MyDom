package br.com.mariapuri.mydom.component.modelmapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;
import br.com.mariapuri.mydom.component.modelmapper.custom.MapperComponentInterface;

//@AllArgsConstructor
@Component
public class PersonMapperComponent implements MapperComponentInterface<PersonModel, PersonDTO> {
	
    private ModelMapper modelMapper;

    public PersonMapperComponent(ModelMapper modelMapper) {
      this.modelMapper = modelMapper;
    }
    
    @Override
    public PersonDTO toDTO(PersonModel personModel) {
        return modelMapper.map(personModel, PersonDTO.class);
    }
    
    @Override
    public List<PersonDTO> toCollectionDTO(List<PersonModel> personModelList){
        return personModelList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public PersonModel toModel(PersonDTO personDTO) {
        return modelMapper.map(personDTO, PersonModel.class);
    }
    
    @Override
    public List<PersonModel> toCollectionModel(List<PersonDTO> personDTOList){
        return personDTOList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
