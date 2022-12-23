package br.com.mariapuri.mydom.component.modelmapper.custom;

import java.util.List;

public interface MapperComponentInterface<M, D> {
  
  public D toDTO(M item);
  public List<D> toCollectionDTO(List<M> itens);
  public M toModel(D itemDTO);
  public List<M> toCollectionModel(List<D> itensDTO);

}
