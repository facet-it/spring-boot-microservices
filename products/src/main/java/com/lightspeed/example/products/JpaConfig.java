package com.lightspeed.example.products;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "database.properties")
public class JpaConfig {

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());

        entityManager.setPackagesToScan(new String[]{"com.lightspeed"});
        JpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(adaptor);
        entityManager.setJpaProperties(extraProperties());

        return entityManager;

    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    public Properties extraProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }

}
