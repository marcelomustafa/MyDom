package br.com.mariapuri.mydom.util.modelmapper.custom;

import java.util.List;

import org.modelmapper.ModelMapper;


public interface SimpleMapper<M, D> {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	
  public D toDTO(M item);
  
  public default List<D> toDTOList(List<M> modelList){
  	return modelList.stream().map(this::toDTO).toList();
  }
  
  public M toModel(D itemDTO);
  
  public default List<M> toModelList(List<D> dtoList){
  	return dtoList.stream().map(this::toModel).toList();
  }	
  
  public default ModelMapper mapper() {
  	return modelMapper;
  }

}
