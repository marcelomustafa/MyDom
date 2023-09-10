package br.com.mariapuri.mydom.app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mariapuri.mydom.app.domain.dto.MyDomDTO;
import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.service.MyDomService;
import br.com.mariapuri.mydom.util.modelmapper.MyDomMapperDTO;
import br.com.mariapuri.mydom.util.pagination.PaginationTools;

@RestController
@RequestMapping("/api")
public class MyDomController {
	
	private final MyDomService myDomService;
	private final MyDomMapperDTO myDomMapperDTO;
	private final PaginationTools pagination;
	
	public MyDomController(
			MyDomService myDomService,
			MyDomMapperDTO myDomMapperDTO,
			PaginationTools pagination
	){
		this.myDomService = myDomService;
		this.myDomMapperDTO = myDomMapperDTO;
		this.pagination = pagination;
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping
    public ResponseEntity<MyDomDTO> getMyDom(){
        return ResponseEntity.status(HttpStatus.OK).body(myDomService.getMyDom());
    }	
	
	@GetMapping("/all")
	public ResponseEntity<List<MyDomDTO>> getAllPerson() {
		var mydoms = myDomService.findAll().stream().map(myDomMapperDTO).toList();
		return ResponseEntity.status(HttpStatus.OK).body(mydoms);
	}

	@PostMapping("/paged")
	public ResponseEntity<Page<MyDomDTO>> filterPersonPaged(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestBody PersonDTO filter) {
		//List<PersonModel> persons = myDomService.filter(filter);
		var mydoms = myDomService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(pagination.ofList(mydoms, pageable, myDomMapperDTO));
	}	
	
}
