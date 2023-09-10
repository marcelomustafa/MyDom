package br.com.mariapuri.mydom.util.modelmapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.mariapuri.mydom.app.domain.dto.MyDomDTO;
import br.com.mariapuri.mydom.app.domain.model.MyDom;


@Component
public class MyDomMapperDTO implements Function<MyDom, MyDomDTO> {
	
	@Override
	public MyDomDTO apply(MyDom mydom) {
		return new MyDomDTO(
				mydom.getName(), 
				mydom.getDescription()
		);
	}

}
