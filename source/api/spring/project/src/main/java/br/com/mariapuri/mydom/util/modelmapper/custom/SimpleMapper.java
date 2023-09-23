package br.com.mariapuri.mydom.util.modelmapper.custom;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface FunctionMapper<M, D> {

  ModelMapper modelMapper = new ModelMapper();
  default ModelMapper mapper() {
    return modelMapper;
  }

  D toDTO(M item);

  default List<D> toDTOList(List<M> modelList) {
    return modelList.stream()
        .map(this::toDTO)
        .toList();
  }

  M toModel(D itemDTO);

  default List<M> toModelList(List<D> dtoList) {
    return dtoList.stream()
        .map(this::toModel)
        .toList();
  }


  default <V> FunctionMapper<V, R> compose(Function<? super V, ? extends T> before) {
    Objects.requireNonNull(before);
    return (V v) -> apply(before.apply(v));
  }

  default <VD> FunctionMapper<M, VD> andThen(Function<? super D, ? extends VD> after) {
    Objects.requireNonNull(after);
    return (M m) -> after.apply(toDTO(m));
  }

  static <M> FunctionMapper<M, M> identity() {
    return m -> m;
  }




}
