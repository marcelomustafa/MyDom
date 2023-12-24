package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serial;
import java.io.Serializable;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_mydom")
@AuditTable(value = "aud_mydom")
@Audited(withModifiedFlag = true)
//@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MyDom extends BaseModel implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;

	@Size(max = 255)
	private String description;

	@Transient
	@NotAudited
	@Size(max = 255)
	private String removeColumnTest;


	public MyDom() {}

	public MyDom(
			@NotBlank String name, 
			@Size(max = 255) String description
	) {

		this.name = name;
		this.description = description;
	
	}	
	
	public MyDom(
			@NotBlank String name, 
			@Size(max = 255) String description,
	    @Size(max = 255) String removeColumnTest
	) {

		this.name = name;
		this.description = description;
		this.removeColumnTest = removeColumnTest;
	
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemoveColumnTest() {
		return removeColumnTest;
	}

	public void setRemoveColumnTest(String removeColumnTest) {
		this.removeColumnTest = removeColumnTest;
	}

	
}
