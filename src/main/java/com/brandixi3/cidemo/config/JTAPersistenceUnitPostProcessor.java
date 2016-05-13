/* Copyright (C) 2015 Brandixi3 (pvt) LTD. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.brandixi3.cidemo.config;

import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

/**
 * The Class JTAPersistenceUnitPostProcessor.
 */
public class JTAPersistenceUnitPostProcessor implements
		PersistenceUnitPostProcessor {

	/** The log. */
	private final Logger log = LoggerFactory
			.getLogger(JTAPersistenceUnitPostProcessor.class);
	
	/** The data source. */
	private DataSource dataSource;
	
	/** The jta enabled. */
	private boolean jtaEnabled = false;

	/* (non-Javadoc)
	 * @see org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor#postProcessPersistenceUnitInfo(org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo)
	 */
	public void postProcessPersistenceUnitInfo(
			MutablePersistenceUnitInfo mutablePersistenceUnitInfo) {
		
		mutablePersistenceUnitInfo.setJtaDataSource(dataSource);
		if (jtaEnabled) {
			mutablePersistenceUnitInfo
					.setTransactionType(PersistenceUnitTransactionType.JTA);
			log.info("Setting up JTA transaction type for merged persistance unit [" + mutablePersistenceUnitInfo.getPersistenceUnitName() +"]");
		} else {
			mutablePersistenceUnitInfo
					.setTransactionType(PersistenceUnitTransactionType.RESOURCE_LOCAL);
			log.info("Setting up RESOURCE_LOCAL transaction type for persistance unit [" + mutablePersistenceUnitInfo.getPersistenceUnitName() +"]");
		}
	}

	/**
	 * Sets the data source.
	 *
	 * @param dataSource the new data source
	 */
	@Required
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Sets the jta enabled.
	 *
	 * @param jtaEnabled the new jta enabled
	 */
	public void setJtaEnabled(boolean jtaEnabled) {
		this.jtaEnabled = jtaEnabled;
	}

}
