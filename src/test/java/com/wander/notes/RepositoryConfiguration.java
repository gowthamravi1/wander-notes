package com.wander.notes;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Instead of main class, Service configuration class used only for repository
 * testing classes.
 *
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.wander.notes" })
@EnableJpaRepositories(basePackages = { "com.wander.notes.repository" })
@EnableTransactionManagement
@ActiveProfiles("test")
public class RepositoryConfiguration {

}
