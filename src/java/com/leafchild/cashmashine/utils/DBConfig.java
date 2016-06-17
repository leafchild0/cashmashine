package com.leafchild.cashmashine.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

@Configuration
@ComponentScan
@EnableTransactionManagement
public class DBConfig {

    // Private fields
    @Autowired
    private Environment env;

    public DBConfig(){}

    /**
     * DataSource definition for database connection. Settings are read from
     * the application.properties file (using the env object).
     */

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionBuilder = new LocalSessionFactoryBean();
        sessionBuilder.setDataSource(dataSource());
        sessionBuilder.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));

        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put(
            "hibernate.dialect",
            env.getProperty("hibernate.dialect"));
        additionalProperties.put(
            "hibernate.show_sql",
            env.getProperty("hibernate.show_sql"));
        additionalProperties.put(
            "hibernate.format_sql",
            env.getProperty("hibernate.format_sql"));
        additionalProperties.put(
            "hibernate.hbm2ddl.auto",
            env.getProperty("hibernate.hbm2ddl.auto"));

        sessionBuilder.setHibernateProperties(additionalProperties);
        return sessionBuilder;
    }
}
