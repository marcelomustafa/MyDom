package br.com.mariapuri.mydom.app.domain.model;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_mydom")
@AuditTable(value = "aud_mydom")
@Audited(withModifiedFlag = true)
//@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MyDomModel extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;

	@Size(max = 255)
	private String description;

	@Transient
	@NotAudited
	@Size(max = 255)
	private String removeColumnTest;

}
