package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(System.getenv("SPRING_DATASOURCE_DRIVER_CLASS_NAME"));
        ds.setUrl(System.getenv("SPRING_DATASOURCE_URL"));
        ds.setUsername(System.getenv("SPRING_DATASOURCE_USERNAME"));
        ds.setPassword(System.getenv("SPRING_DATASOURCE_PASSWORD"));
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
