package br.com.mariapuri.mydom.util.modelmapper.custom;

import java.util.List;

public interface MapperInterface<M, D> {
	
  public D toDTO(M item);
  public List<D> toCollectionDTO(List<M> itens);
  public M toModel(D itemDTO);
  public List<M> toCollectionModel(List<D> itensDTO);

}
