package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DualDataSourceConfig {

    // ===============================
    // POSTGRES (LUÔN ACTIVE - local/prod)
    // ===============================
    @Bean(name = "pgDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource pgDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pgJdbcTemplate")
    public JdbcTemplate pgJdbcTemplate(@Qualifier("pgDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // ===============================
    // MYSQL (CHỈ LOCAL)
    // ===============================
    @Bean(name = "mysqlDataSource")
    @Profile("local")
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlJdbcTemplate")
    @Profile("local")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
