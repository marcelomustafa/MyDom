package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;

//@Getter
//@Setter
@Entity
@Table(name = "tbl_person")
@AuditTable(value = "aud_person")
@Audited(withModifiedFlag = true)
//@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonModel extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PersonModel(){}
	
	public PersonModel(String name, String documento, String email) {
		this.name = name;
		this.documento = documento;
		this.email = email;
	}
	

	@Column(nullable = false, length = 70)
	private String name;

	@Column(nullable = false, length = 20)
	private String documento;

	@Column(nullable = false, length = 70, unique = true)
	@Email
	private String email;

//    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
//    private UserModel usersModel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//		public UserModel getUsersModel() {
//			return usersModel;
//		}
//
//		public void setUsersModel(UserModel usersModel) {
//			this.usersModel = usersModel;
//		}

}
