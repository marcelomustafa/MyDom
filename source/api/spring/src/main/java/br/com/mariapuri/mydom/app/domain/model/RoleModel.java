package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import br.com.mariapuri.mydom.enums.RoleNameType;
import lombok.EqualsAndHashCode;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "tbl_role")
@AuditTable(value = "aud_role")
@Audited(withModifiedFlag = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleModel implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(name = "name", nullable = false, unique = true)
	private RoleNameType name;

	@NotAudited
	@CreatedBy
	private String createdBy;

	@NotAudited
	@CreatedDate
	private Instant createdDate;

	@NotAudited
	@LastModifiedBy
	private String modifiedBy;

	@NotAudited
	@LastModifiedDate
	private Instant modifiedDate;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public RoleNameType getName() {
		return name;
	}

	public void setName(RoleNameType name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Instant getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Instant modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.getName().toString();
	}

}
