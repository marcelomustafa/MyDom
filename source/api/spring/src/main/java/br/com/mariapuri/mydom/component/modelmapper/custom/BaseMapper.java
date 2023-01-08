package br.com.mariapuri.mydom.component.modelmapper.custom;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseMapper<M, D> implements MapperInterface<M, D> {

	@Autowired
	protected ModelMapper modelMapper;

	
	public List<D> toCollectionDTO(List<M> modelList) {
		return modelList.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public List<M> toCollectionModel(List<D> dtoList) {
		return dtoList.stream().map(this::toModel).collect(Collectors.toList());
	}
	
}