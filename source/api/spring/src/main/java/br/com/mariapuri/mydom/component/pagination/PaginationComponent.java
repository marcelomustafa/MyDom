package br.com.mariapuri.mydom.component.pagination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.mariapuri.mydom.component.modelmapper.custom.MapperComponentInterface;

@Component
public class PaginationComponent {

  public <E> Page<E> ofList(List<E> content, Pageable pageable){
    return ofList(content, pageable, null);
  }

  @SuppressWarnings(value={"unchecked", "rawtypes"})
  public <R, E> Page<R> ofList(List<E> content, Pageable pageable, MapperComponentInterface mapper){
    
    var contentSize =  content.size();
    var firstItem = pageable.getPageNumber() * pageable.getPageSize();

    var lastItem = firstItem + pageable.getPageSize();
    if(lastItem > contentSize) {
      lastItem = contentSize;
    }
    
    List<E> contentPageSubList = content.subList(firstItem, lastItem);
    
    List<R> contentPage = new ArrayList<>();
    if(mapper != null) {
      contentPage = mapper.toCollectionDTO(contentPageSubList);
    }else {
      contentPage = (List<R>) contentPageSubList;
    }
    
    PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
    return new PageImpl<>(contentPage,pageRequest,contentSize);
  }
  
}
