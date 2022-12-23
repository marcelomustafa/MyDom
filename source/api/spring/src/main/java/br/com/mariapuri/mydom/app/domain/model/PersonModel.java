package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

//@Getter
//@Setter
@Entity
@Table(name = "tbl_person")
@AuditTable(value="aud_person")
@Audited(withModifiedFlag = true)
//@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonModel implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
   // @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;	      
    
    @Column(nullable = false, length = 70)
    private String name; 
    
    @Column(nullable = false, length = 20)
    private String documento; 
    
    @Column(nullable = false, length = 70, unique = true)
    @Email
    private String email; 
    
    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    private UserModel usersModel;
    
    @NotAudited
    @CreatedBy
    private String createdBy;
    
    @NotAudited
    @CreatedDate
    private Instant createdDate;
    
    @NotAudited
    @LastModifiedBy
    private String lastModifiedBy;
    
    @NotAudited
    @LastModifiedDate
    private Instant lastModifiedDate;

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

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

		public UserModel getUsersModel() {
			return usersModel;
		}

		public void setUsersModel(UserModel usersModel) {
			this.usersModel = usersModel;
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

		public String getLastModifiedBy() {
			return lastModifiedBy;
		}

		public void setLastModifiedBy(String lastModifiedBy) {
			this.lastModifiedBy = lastModifiedBy;
		}

		public Instant getLastModifiedDate() {
			return lastModifiedDate;
		}

		public void setLastModifiedDate(Instant lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}    

    
}
