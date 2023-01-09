package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;
import br.com.mariapuri.mydom.enums.RoleNameType;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "tbl_role")
@AuditTable(value = "aud_role")
@Audited(withModifiedFlag = true)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleModel extends BaseModel implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "name", nullable = false, unique = true)
	private RoleNameType name;

	public RoleNameType getName() {
		return name;
	}

	public void setName(RoleNameType name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.getName().toString();
	}

}
