package br.com.mariapuri.mydom.config.audit;

import javax.persistence.EntityManagerFactory;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "customAuditProvider")
public class AuditConfiguration {

	private final EntityManagerFactory entityManagerFactory;

	AuditConfiguration(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Bean
	AuditReader auditReader() {
		return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
	}

	public class JpaConfig {

		@Bean
		AuditorAware<String> customAuditProvider() {
			return new AuditAwareImpl();
		}

	}

}
