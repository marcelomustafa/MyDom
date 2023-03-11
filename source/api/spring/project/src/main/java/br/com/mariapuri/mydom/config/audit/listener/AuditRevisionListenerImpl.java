package br.com.mariapuri.mydom.config.audit.listener;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import br.com.mariapuri.mydom.config.audit.domain.AuditRevisionEntity;

public class AuditRevisionListenerImpl implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			Object principal = auth.getPrincipal();
			
			String username = null;
			
			if(principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			
			
			String ip = null;
			
			//OAuth2AuthenticationDetails
			if(auth.getDetails() instanceof WebAuthenticationDetails) {
				ip = ((WebAuthenticationDetails) auth.getDetails()).getRemoteAddress();
			}
			
			AuditRevisionEntity auditEntity = (AuditRevisionEntity) revisionEntity;
			auditEntity.setUserId(username);
			auditEntity.setUsername(username);
			auditEntity.setUserIp(ip);
			
			
		}
	}

}
