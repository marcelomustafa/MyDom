package br.com.mariapuri.mydom.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mariapuri.mydom.app.domain.dto.MyDomDTO;
import br.com.mariapuri.mydom.app.service.MyDomService;

//@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class MyDomController {
	
	private final MyDomService myDomService;
	
	public MyDomController(MyDomService myDomService){
		this.myDomService = myDomService;
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping
    public ResponseEntity<MyDomDTO> getMyDom(){
        return ResponseEntity.status(HttpStatus.OK).body(myDomService.getMyDom());
    }	
	
}
