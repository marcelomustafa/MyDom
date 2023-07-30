package br.com.mariapuri.mydom.config.security.payload;

import java.time.Instant;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.app.domain.model.custom.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
  
}
