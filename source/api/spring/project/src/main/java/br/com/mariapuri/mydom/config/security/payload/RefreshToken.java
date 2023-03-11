package br.com.mariapuri.mydom.config.security.payload;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;

//@Getter
//@Setter
@Entity
@Table(name = "tbl_refresh_token")
//@AuditTable(value = "aud_user")
//@Audited(withModifiedFlag = true)
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RefreshToken extends BaseModel {
	private static final long serialVersionUID = 1L;

	@OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserModel user;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private Instant expiryDate;
  

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}	
  
  

}
