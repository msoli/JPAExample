package com.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by SISTEMAS03 on 04/05/2016.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.example.model", "com.example.repository"},
        entityManagerFactoryRef = "heroesEntityManagerFactory",
        transactionManagerRef = "heroesTransactionManager"
)
public class DatabaseConfig {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DatabaseConfig.class);

    @Inject
    private AngularProperties angularProperties;


//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring1.datasource")
//    public DataSource heroesDataSource() {
//
//        DataSource ds = DataSourceBuilder.create().build();
//        return ds;
//    }


    @Primary
    @Bean(destroyMethod = "close")
    public DataSource heroesDataSource() {


        Properties props = new Properties();
        props.setProperty("dataSourceClassName", angularProperties.getDatabase().getHikari().getDataSourceClassName());
        props.setProperty("connectionTestQuery", angularProperties.getDatabase().getHikari().getConnectionTestQuery());
        props.setProperty("minimumIdle", angularProperties.getDatabase().getHikari().getMinimumIdle());
        props.setProperty("maximumPoolSize", angularProperties.getDatabase().getHikari().getMaximumPoolSize());
        props.setProperty("poolName", angularProperties.getDatabase().getHikari().getPoolName());

//        props.setProperty("dataSource.url", angularProperties.getDatabase().getUrl());
        props.setProperty("dataSource.url", "jdbc:mysql://localhost:3306/heroes");
        props.setProperty("dataSource.user", angularProperties.getDatabase().getUsername());
        props.setProperty("dataSource.password", angularProperties.getDatabase().getPassword());
        props.setProperty("dataSource.cachePrepStmts", angularProperties.getDatabase().getCachePrepStmts());
        props.setProperty("dataSource.prepStmtCacheSize", angularProperties.getDatabase().getPrepStmtCacheSize());
        props.setProperty("dataSource.prepStmtCacheSqlLimit", angularProperties.getDatabase().getPrepStmtCacheSqlLimit());


        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);

        return ds;

    }


    @Primary
    @Bean
    PlatformTransactionManager heroesTransactionManager() {
        return new JpaTransactionManager(heroesEntityManagerFactory().getObject());
    }


    @PersistenceContext(unitName = "primary")
    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean heroesEntityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(heroesDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPersistenceUnitName("primary");
        factoryBean.setPackagesToScan("com.example.model", "com.example.repository");

        return factoryBean;
    }


}