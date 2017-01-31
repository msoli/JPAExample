package com.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by SISTEMAS03 on 04/05/2016.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.example.modelv2", "com.example.repositoryv2"},
        entityManagerFactoryRef = "heroesV2EntityManagerFactory",
        transactionManagerRef = "heroesV2TransactionManager"
)
public class DatabaseConfigHeroesV2 {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DatabaseConfigHeroesV2.class);


    @Inject
    private AngularProperties angularProperties;

    @Bean(name = "heroesV2dataSource",destroyMethod = "close")
    public DataSource heroesV2dataSource() {


        Properties props = new Properties();
        props.setProperty("dataSourceClassName", angularProperties.getDatabase().getHikari().getDataSourceClassName());
        props.setProperty("connectionTestQuery", angularProperties.getDatabase().getHikari().getConnectionTestQuery());
        props.setProperty("minimumIdle", angularProperties.getDatabase().getHikari().getMinimumIdle());
        props.setProperty("maximumPoolSize", angularProperties.getDatabase().getHikari().getMaximumPoolSize());
        props.setProperty("poolName", angularProperties.getDatabase().getHikari().getPoolName()+"2");

        props.setProperty("dataSource.url", angularProperties.getDatabase().getUrlHeroesV2());
        props.setProperty("dataSource.user", angularProperties.getDatabase().getUsername());
        props.setProperty("dataSource.password", angularProperties.getDatabase().getPassword());
        props.setProperty("dataSource.cachePrepStmts", angularProperties.getDatabase().getCachePrepStmts());
        props.setProperty("dataSource.prepStmtCacheSize", angularProperties.getDatabase().getPrepStmtCacheSize());
        props.setProperty("dataSource.prepStmtCacheSqlLimit", angularProperties.getDatabase().getPrepStmtCacheSqlLimit());


        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);

        return ds;

    }


    @Bean
    PlatformTransactionManager heroesV2TransactionManager() {
        return new JpaTransactionManager(heroesV2EntityManagerFactory().getObject());
    }

    @PersistenceContext(unitName = "secondary")
    @Bean
    LocalContainerEntityManagerFactoryBean heroesV2EntityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(heroesV2dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPersistenceUnitName("secondary");
        factoryBean.setPackagesToScan("com.example.modelv2", "com.example.repositoryv2");

        return factoryBean;
    }


}