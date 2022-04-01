package com.asia.leadsgen.fmerch.config;

import com.asia.leadsgen.fmerch.interceptor.AppInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Environment environment;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");

        Properties properties = new Properties();
        properties.put("url", environment.getProperty("database.psp.url"));
        properties.put("user", environment.getProperty("database.psp.user"));
        properties.put("password", environment.getProperty("database.psp.password"));
        hikariConfig.setDataSourceProperties(properties);

        hikariConfig.setPoolName("PSP_DB_POOL");
        hikariConfig.setMinimumIdle(Integer.parseInt(Objects.requireNonNull(environment.getProperty("datasource.hikari.minimum-idle"))));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("datasource.hikari.maximum-pool-size"))));
        hikariConfig.setIdleTimeout(Long.parseLong(Objects.requireNonNull(environment.getProperty("datasource.hikari.idle-timeout"))));
        hikariConfig.setValidationTimeout(Long.parseLong(Objects.requireNonNull(environment.getProperty("datasource.hikari.validation-timeout"))));
        hikariConfig.setConnectionTimeout(Long.parseLong(Objects.requireNonNull(environment.getProperty("datasource.hikari.connection-timeout"))));
        hikariConfig.setAutoCommit(Boolean.parseBoolean(environment.getProperty("datasource.hikari.auto-commit")));

        return hikariConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppInterceptor());
    }

}
