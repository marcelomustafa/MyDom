package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_person")
@AuditTable(value = "aud_person")
@Audited(withModifiedFlag = true)
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonModel extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Column(nullable = false, length = 70)
	private String name;

	@Column(nullable = false, length = 20)
	private String documento;

	@Column(nullable = false, length = 70, unique = true)
	@Email
	private String email;

//    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
//    private UserModel usersModel;

}
