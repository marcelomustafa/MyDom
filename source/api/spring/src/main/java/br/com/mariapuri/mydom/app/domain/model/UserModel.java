package br.com.mariapuri.mydom.app.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;

//@Getter
//@Setter
@Entity
@Table(name = "tbl_user")
@AuditTable(value="aud_user")
@Audited(withModifiedFlag = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserModel implements UserDetails, Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    
    @OneToOne
    @JoinColumn(name="person_id")
    private PersonModel person;
    
    @Column(nullable = false, length = 70, unique = true)
    private String userName;    

    @Column(nullable = false, length = 255)
    private String password; 
    
    @ManyToMany()
    @AuditJoinTable(name = "aud_user_role")
    @JoinTable(name = "tbl_user_role" ,
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleModel> roles;    
    
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
			
			//var roleMapper = new ModelMapper();
			
					
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
