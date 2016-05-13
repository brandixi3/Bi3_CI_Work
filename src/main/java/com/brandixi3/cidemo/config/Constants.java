/* Copyright (C) 2015 Brandixi3 (pvt) LTD. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.brandixi3.cidemo.config;

/**
 * @author JanithB
 *
 */
public final class Constants {
	
    private Constants() {
        // Prevent instantiation
    }

    public final class Database {

        private Database() {

        }

        public static final String DATABASE_DRIVER_DATASOURCE_CLAZZ = "db.driver.datasourc.clazz";
        public static final String DATABASE_DRIVER_CLAZZ = "db.driver.clazz";
        public static final String DATABASE_URL = "db.url";
        public static final String DATABASE_USERNAME = "db.username";
        public static final String DATABASE_PWD = "db.pwd";
        public static final String DATABASE_CONNECTION_TEST_QUERY = "db.connection.test.query";
        public static final String DATABASE_MAX_POOL_SIZE = "db.max.pool.size";

    }

    /**
     * Constants related to application settings
     */
    public final class Application {
        private Application() {
            // Prevent instantiation
        }
    }
}
