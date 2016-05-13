/* Copyright (C) 2015 Brandixi3 (pvt) LTD. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.brandixi3.cidemo.config;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;

public class MultiConfigAwarePersistenceUnitManager extends
		DefaultPersistenceUnitManager {

	private final Logger log = LoggerFactory
			.getLogger(MultiConfigAwarePersistenceUnitManager.class);

	private boolean strict;
	private String persistenceUnitName;

	protected void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
		super.postProcessPersistenceUnitInfo(pui);

		// skip the template from post processing
		if (isApplicationPersistenceUnit(pui)) {
			return;
		}
		final MutablePersistenceUnitInfo mainPui = getMainPersistenceUnitInfo(pui);
		if (strict) {
			pui.addJarFileUrl(pui.getPersistenceUnitRootUrl());
		}

		if (mainPui != null) {
			if (strict) {

				log.info("Merging existing jar file urls "
						+ mainPui.getJarFileUrls() + " to persistence unit ["
						+ pui.getPersistenceUnitName() + "]");

				copyPersistenceUnit(mainPui, pui);

				log.info("Persistence unit[" + pui.getPersistenceUnitName()
						+ "] has now " + "the following jar file urls "
						+ pui.getJarFileUrls() + "");

			} else {

				log.info("Merging information from ["
						+ pui.getPersistenceUnitName() + "] " + "to ["
						+ mainPui.getPersistenceUnitName() + "]");

				mergePersistenceUnit(pui, mainPui);

				log.info("Persistence unit[" + mainPui.getPersistenceUnitName()
						+ "] has now " + "the following jar file urls "
						+ mainPui.getJarFileUrls() + "");

			}
		} else if (!pui.getPersistenceUnitName().equals(persistenceUnitName)) {
			log.debug("Adding persistence unit ["
					+ pui.getPersistenceUnitName() + "] as is (no merging)");
		}
	}

	public void setStrict(boolean strict) {
		this.strict = strict;
	}

	public void setPersistenceUnitName(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}

	protected void mergePersistenceUnit(MutablePersistenceUnitInfo from,
			MutablePersistenceUnitInfo to) {
		if (from.getManagedClassNames().size() != 0) {
			for (String s : from.getManagedClassNames()) {
				log.debug("Adding entity [" + s + "]");
				to.addManagedClassName(s);
			}
			log.debug("Added [" + from.getManagedClassNames().size()
					+ "] managed classes to " + "persistence unit["
					+ to.getPersistenceUnitName() + "]");
		} else {
			to.addJarFileUrl(from.getPersistenceUnitRootUrl());
			log.debug("Added [" + from.getPersistenceUnitRootUrl()
					+ "] for entity scanning " + "to persistence unit["
					+ to.getPersistenceUnitName() + "]");
		}
	}

	protected void copyPersistenceUnit(MutablePersistenceUnitInfo from,
			MutablePersistenceUnitInfo to) {
		for (URL url : from.getJarFileUrls()) {
			to.addJarFileUrl(url);
		}
		for (String s : from.getManagedClassNames()) {
			to.addManagedClassName(s);
		}
	}

	private boolean isApplicationPersistenceUnit(MutablePersistenceUnitInfo pui) {
		return (!strict && persistenceUnitName.equals(pui
				.getPersistenceUnitName()));
	}

	private MutablePersistenceUnitInfo getMainPersistenceUnitInfo(
			MutablePersistenceUnitInfo pui) {

		if (strict) {
			return getPersistenceUnitInfo(pui.getPersistenceUnitName());
		} else if (pui.getPersistenceUnitName().startsWith(persistenceUnitName)) {
			// We have a match, retrieve our persistence unit name then
			final MutablePersistenceUnitInfo result = getPersistenceUnitInfo(persistenceUnitName);
			if (result == null) {
				throw new IllegalStateException(
						"No persistence unit found with name ["
								+ persistenceUnitName
								+ "] "
								+ "so no merging is possible. It usually means that the bootstrap-persistence.xml has not been "
								+ "included in the list of persistence.xml location(s). Check your configuration as it "
								+ "should be the first in the list!");
			}
			return result;
		}
		// Nothing has been found
		return null;
	}
}