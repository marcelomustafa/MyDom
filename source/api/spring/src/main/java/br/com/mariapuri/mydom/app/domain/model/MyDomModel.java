package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_mydom")
@AuditTable(value="aud_mydom")
@Audited(withModifiedFlag = true)
//@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MyDomModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    
	@NotBlank
	private String name;
	
	@Size(max=255)
	private String description;   
	
	@Transient
	@NotAudited
	@Size(max=255)
	private String removeColumnTest;
    
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

//	public String getRemoveColumnTest() {
//		return "removeColumnTest";
//	} 
//	
	
	
}
