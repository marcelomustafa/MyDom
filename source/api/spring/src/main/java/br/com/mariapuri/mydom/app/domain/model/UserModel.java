package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;

//@Getter
//@Setter
@Entity
@Table(name = "tbl_user")
@AuditTable(value = "aud_user")
@Audited(withModifiedFlag = true)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserModel extends BaseModel implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

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
	private List<RoleModel> roles;

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

	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// var roleMapper = new ModelMapper();

//			this.getRoles().stream()
//				.forEach(item -> rolesSecurity.add(roleMapper.map(item, RoleSecurity.class)));

//			var roleMapper = new ModelMapper();
////			//var
//			List<RoleSecurity> rolesSecurity = this.getRoles().stream()
//					.map(item -> roleMapper.map(item, RoleSecurity.class))
//					.toList() ;
//			
//			//Collection<RoleSecurity> roleSecurity = Collection
//			
		return getRoles();

	}

}
