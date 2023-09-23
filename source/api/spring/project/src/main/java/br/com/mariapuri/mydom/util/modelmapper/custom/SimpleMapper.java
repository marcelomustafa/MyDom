package br.com.mariapuri.mydom.util.modelmapper.custom;

import org.modelmapper.ModelMapper;

import java.util.List;

public interface SimpleMapper<M, D> {

  ModelMapper modelMapper = new ModelMapper();
  default ModelMapper mapper() {
    return modelMapper;
  }

  D toDTO(M item);

  default List<D> toDTOList(List<M> modelList) {
    return modelList
        .stream()
        .map(this::toDTO)
        .toList();
  }

  M toModel(D itemDTO);

  default List<M> toModelList(List<D> dtoList) {
    return dtoList
        .stream()
        .map(this::toModel)
        .toList();
  }

}
