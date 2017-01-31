package com.example.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Inject
    private AngularProperties angularProperties;

//    @Inject
//    @Qualifier(value = "heroesV2dataSource")
//    private DataSource heroesV2dataSource;


    @Bean(name = "sessionFactoryHib")
    public LocalSessionFactoryBean getSessionFactory(@Qualifier("heroesV2dataSource") DataSource heroesV2dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(heroesV2dataSource);

        sessionFactory.setPackagesToScan(new String[]{"com.example.modelv2"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", angularProperties.getHibernate().getDialect());
        properties.put("hibernate.show_sql", angularProperties.getHibernate().getShowSql());
        properties.put("hibernate.format_sql", angularProperties.getHibernate().getFormatSql());
        properties.put("hibernate.jdbc.batch_size", 30);
        return properties;
    }


//    @Bean(name = "transactionManagerHib")
//    @Inject
//    public HibernateTransactionManager getTransactionManager(@Qualifier("sessionFactoryHib") SessionFactory sessionFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//        return txManager;
//    }

}