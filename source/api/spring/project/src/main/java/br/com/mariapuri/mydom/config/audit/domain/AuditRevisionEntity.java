package br.com.mariapuri.mydom.config.audit.domain;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import br.com.mariapuri.mydom.config.audit.listener.AuditRevisionListenerImpl;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aud_revision_info")
@AttributeOverrides({ 
	@AttributeOverride(name = "timestamp", column = @Column(name = "rev_timestamp")),
//  @AttributeOverride(name = "id", column = @Column(name = "revision_id")) 
})
@RevisionEntity(AuditRevisionListenerImpl.class)
public class AuditRevisionEntity extends DefaultRevisionEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private String userId;	
	
	@Column(name = "user_ip")
	private String userIp;	
	
	@Column(name = "username")
	private String username;

}