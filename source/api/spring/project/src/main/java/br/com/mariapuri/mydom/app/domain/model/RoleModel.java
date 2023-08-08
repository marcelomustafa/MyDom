package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;
import br.com.mariapuri.mydom.enums.RoleNameType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_role")
@AuditTable(value = "aud_role")
@Audited(withModifiedFlag = true)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleModel extends BaseModel implements Serializable {
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

}
