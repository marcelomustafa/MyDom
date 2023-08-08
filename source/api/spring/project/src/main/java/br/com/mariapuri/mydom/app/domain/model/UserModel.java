package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_user")
@AuditTable(value = "aud_user")
@Audited(withModifiedFlag = true)
public class UserModel extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public UserModel() {}
	
	public UserModel(String name, String documento, String email, String userName, String password) {
		this.person = new PersonModel(name, documento, email);
		this.userName = userName;
		this.password = password;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private PersonModel person;

	@Column(nullable = false, length = 70, unique = true)
	private String userName;

	@Column(nullable = false, length = 255)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@AuditJoinTable(name = "aud_user_role")
	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleModel> roles;
	
	
	public PersonModel getPerson() {
		return person;
	}

	public void setPerson(PersonModel person) {
		this.person = person;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

}
