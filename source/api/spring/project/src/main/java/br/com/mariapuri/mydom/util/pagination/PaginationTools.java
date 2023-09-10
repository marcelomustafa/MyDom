package br.com.mariapuri.mydom.util.pagination;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.mariapuri.mydom.util.modelmapper.custom.SimpleMapper;

@Component
public class PaginationTools {
	
  public <E> Page<E> ofList(List<E> content, Pageable pageable){
  	List<E> contentPage = getContentPage(content, pageable);
  	return getPage(pageable,contentPage,content.size());
  }

  public <R, E> Page<R> ofList(List<E> content, Pageable pageable, Function<E,R> mapper){
    var contentPage = getContentPage(content, pageable).stream().map(mapper).toList();
    return getPage(pageable,contentPage,content.size());
  }  

  public <R, E> Page<R> ofList(List<E> content, Pageable pageable, SimpleMapper<E,R> mapper){
    List<R> contentPage = mapper.toDTOList(getContentPage(content, pageable));
    return getPage(pageable,contentPage,content.size());
  }
  

	private <E> List<E> getContentPage(List<E> content, Pageable pageable){
		
    var contentSize =  content.size();
    var firstItem = pageable.getPageNumber() * pageable.getPageSize();

    var lastItem = firstItem + pageable.getPageSize();
    if(lastItem > contentSize) {
      lastItem = contentSize;
    }
    
    return content.subList(firstItem, lastItem);
		
	}
	
	private <R> PageImpl<R> getPage(Pageable pageable, List<R> contentPage, int contentSize) {
    PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
    return new PageImpl<>(contentPage,pageRequest,contentSize);		
	}
	  
  
  
}
