/* Copyright (C) 2015 Brandixi3 (pvt) LTD. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.brandixi3.cidemo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableTransactionManagement
public class PersistanceConfig {

	@Autowired
	private Environment env;

	private static final String PUName = "demo";

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(env
				.getProperty(Constants.Database.DATABASE_DRIVER_DATASOURCE_CLAZZ));
		config.addDataSourceProperty("url",
				env.getProperty(Constants.Database.DATABASE_URL));
		config.addDataSourceProperty("user",
				env.getProperty(Constants.Database.DATABASE_USERNAME));
		config.addDataSourceProperty("password",
				env.getProperty(Constants.Database.DATABASE_PWD));
		config.setConnectionTestQuery(env
				.getProperty(Constants.Database.DATABASE_CONNECTION_TEST_QUERY));
		config.setMaximumPoolSize(Integer.valueOf(env
				.getProperty(Constants.Database.DATABASE_MAX_POOL_SIZE)));
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setPersistenceUnitName(PersistanceConfig.PUName);
		entityManagerFactory.setPersistenceUnitManager(defaultPersistenceUnitManager());
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setPackagesToScan("com.brandixi3.cidemo.model");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		additionalProperties.put("hibernate.show_sql", true);
		//additionalProperties.put("hibernate.hbm2ddl.auto", "update");
		entityManagerFactory.setJpaProperties(additionalProperties);
		return entityManagerFactory;
	}

	@Bean
	public PersistenceUnitPostProcessor persistenceUnitPostProcessor() {

		JTAPersistenceUnitPostProcessor postProcessor = new JTAPersistenceUnitPostProcessor();
		postProcessor.setDataSource(dataSource());
		postProcessor.setJtaEnabled(false);
		return postProcessor;
	}

	@Bean
	public DefaultPersistenceUnitManager defaultPersistenceUnitManager() {

		MultiConfigAwarePersistenceUnitManager manager = new MultiConfigAwarePersistenceUnitManager();
		manager.setPersistenceXmlLocations(new String[] { "classpath*:META-INF/persistance.xml" });
		manager.setPersistenceUnitName(PersistanceConfig.PUName);
		manager.setStrict(false);
		manager.setDefaultDataSource(dataSource());
		manager.setPersistenceUnitPostProcessors(persistenceUnitPostProcessor());
		return manager;

	}

	/**
	 * Transaction manager.
	 *
	 * @return the platform transaction manager
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return tm;
	}

	/**
	 * Exception translation.
	 *
	 * @return the persistence exception translation post processor
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	  public SpringLiquibase liquibaseConfig() {
	    SpringLiquibase liquibase = new SpringLiquibase();
	    liquibase.setDataSource(dataSource());
	    liquibase.setChangeLog("classpath:db-changelog.xml");
	    return liquibase;
	  }
}