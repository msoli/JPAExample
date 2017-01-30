package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by SISTEMAS03-PC on 03/01/2017.
 */
@ConfigurationProperties(prefix = "mikeio", ignoreUnknownFields = false)
public class AngularProperties {

    private final Database database = new Database();

    private final Hibernate hibernate = new Hibernate();

    public Hibernate getHibernate() {
        return hibernate;
    }

    public Database getDatabase() {
        return database;
    }

    @Data
    public static class Database {

        private String url;
        private String username;
        private String password;
        private String driverClassName;
        private String cachePrepStmts;
        private String prepStmtCacheSize;
        private String prepStmtCacheSqlLimit;
        private Hikari hikari;
    }


    @Data
    public static class Hibernate {

        private String dialect;
        private String showSql;
        private String formatSql;
    }

    @Data
    public static class Hikari {

        private String dataSourceClassName;
        private String connectionTestQuery;
        private String minimumIdle;
        private String maximumPoolSize;
        private String poolName;
    }

}
