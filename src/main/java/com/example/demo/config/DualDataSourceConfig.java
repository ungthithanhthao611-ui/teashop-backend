package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DualDataSourceConfig {

    // ===============================
    // POSTGRES (TARGET - SUPABASE)
    // ===============================
    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource pgDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "pgJdbcTemplate")
    public JdbcTemplate pgJdbcTemplate(
            @Qualifier("pgDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // ===============================
    // MYSQL (SOURCE - LOCAL)
    // ===============================
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource mysqlDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(
            @Qualifier("mysqlDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
