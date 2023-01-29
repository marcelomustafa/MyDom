package br.com.mariapuri.mydom.app.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.PersonModel;
import br.com.mariapuri.mydom.app.service.PersonService;
import br.com.mariapuri.mydom.util.modelmapper.PersonMapper;
import br.com.mariapuri.mydom.util.pagination.PaginationTools;

//@AllArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private PaginationTools pagination;


	// @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_TRAINEE')")
	// @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TRAINEE')")
	@GetMapping("/all")
	public ResponseEntity<List<PersonDTO>> getAllPerson() {
		var persons = personMapper.toCollectionDTO(personService.findAll());
		return ResponseEntity.status(HttpStatus.OK).body(persons);
	}

	@GetMapping("/paged")
	public ResponseEntity<Page<PersonDTO>> filterPersonPaged(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestBody PersonDTO filter) {
		List<PersonModel> persons = personService.filter(filter);
		return ResponseEntity.status(HttpStatus.OK).body(pagination.ofList(persons, pageable, personMapper));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") UUID id) {		
		var personDTO = personMapper.toDTO(personService.findById(id).get());
		return ResponseEntity.status(HttpStatus.OK).body(personDTO);
	}

	// @PreAuthorize("hasAuthority('student:write')")
	@PostMapping
	public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
		var newPerson = personService.save(personMapper.toModel(personDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(personMapper.toDTO(newPerson));
	}

	// @PreAuthorize("hasAuthority('student:write')")
	@PutMapping("/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") UUID id, @RequestBody PersonDTO personDTO)
			throws IllegalAccessException, InvocationTargetException {
		var existPerson = personService.findById(id);

		if (!existPerson.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(personDTO);
		} else {
			var itPerson = existPerson.get();
			BeanUtils.copyProperties(personDTO, itPerson);
			var newPerson = personService.save(itPerson);
			return ResponseEntity.status(HttpStatus.OK).body(personMapper.toDTO(newPerson));
		}
	}

	@PatchMapping("/{id}/set-status")
	public ResponseEntity<Object> patchPersonStatus(
			@PathVariable("id") UUID id,
			@PathVariable("status") String status) {

		var existPerson = personService.findById(id);
		if (!existPerson.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not founded.");
		} else {
			var itPerson = existPerson.get();
			// TODO: update status of person
			return ResponseEntity.status(HttpStatus.OK).body(personMapper.toDTO(itPerson));
		}
	}

	// @PreAuthorize("hasAuthority('student:write')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePerson(@PathVariable("id") UUID id) {
		
		var existPerson = personService.findById(id);
		if (!existPerson.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not founded");
		} else {
			var itPerson = existPerson.get();
			personService.delete(itPerson);
			return ResponseEntity.status(HttpStatus.OK).body(personMapper.toDTO(itPerson));
		}
		
	}

	@GetMapping("/report-of-persons")
	public ResponseEntity<byte[]> getReportPerson(@RequestBody PersonDTO filter) throws Throwable  {
		
		var personDTO = personMapper.toCollectionDTO(personService.filter(filter));
		if(personDTO.isEmpty()) {
			//new ThrowExceptionHandler();
			return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		
		byte[] report = personService.getReportPerson(personDTO, filter);
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(report);
		
	}

	@GetMapping("/sheet-of-persons")
	public ResponseEntity<byte[]> getSheetPerson(@RequestBody PersonDTO filter) throws Throwable  {

		var personDTO = personMapper.toCollectionDTO(personService.filter(filter));
		if(personDTO.isEmpty()) {
			//new ThrowExceptionHandler();
			return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}		
		
		byte[] sheet = personService.getSheetPerson(personDTO, filter);
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_TYPE)
				.contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(sheet);
	}

}
