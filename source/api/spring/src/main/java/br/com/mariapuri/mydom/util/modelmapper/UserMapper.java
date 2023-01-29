package br.com.mariapuri.mydom.util.modelmapper;

import org.springframework.stereotype.Component;

import br.com.mariapuri.mydom.app.domain.dto.UserDTO;
import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.util.modelmapper.custom.BaseMapper;

@Component
public class UserMapper extends BaseMapper<UserModel, UserDTO> {

	@Override
	public UserDTO toDTO(UserModel item) {
		return modelMapper.map(item, UserDTO.class);
	}

	@Override
	public UserModel toModel(UserDTO itemDTO) {
		return modelMapper.map(itemDTO, UserModel.class);
	}

}
