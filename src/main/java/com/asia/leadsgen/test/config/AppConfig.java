package com.asia.leadsgen.test.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.asia.leadsgen.test.interceptor.AppInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@SuppressWarnings("deprecation")
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
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
//        hikariConfig.setDataSourceClassName("org.hibernate.dialect.MySQL5InnoDBDialect");
//
//        Properties properties = new Properties();
//        properties.put("url", environment.getProperty("database.psp.url"));
//        properties.put("user", environment.getProperty("database.psp.user"));
//        properties.put("password", environment.getProperty("database.psp.password"));
//        hikariConfig.setDataSourceProperties(properties);

//        hikariConfig.setPoolName("PSP_DB_POOL");
//        hikariConfig.setMinimumIdle(Integer.parseInt(Objects.requireNonNull(environment.getProperty("datasource.hikari.minimum-idle"))));
//        hikariConfig.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("datasource.hikari.maximum-pool-size"))));
//        hikariConfig.setIdleTimeout(Long.parseLong(Objects.requireNonNull(environment.getProperty("datasource.hikari.idle-timeout"))));
//        hikariConfig.setValidationTimeout(Long.parseLong(Objects.requireNonNull(environment.getProperty("datasource.hikari.validation-timeout"))));
//        hikariConfig.setConnectionTimeout(Long.parseLong(Objects.requireNonNull(environment.getProperty("datasource.hikari.connection-timeout"))));
//        hikariConfig.setAutoCommit(Boolean.parseBoolean(environment.getProperty("datasource.hikari.auto-commit")));
//
//        return hikariConfig;

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/customize");
		config.setUsername("root");
		config.setPassword("");
		config.setDriverClassName("com.mysql.jdbc.Driver");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		return new HikariDataSource(config);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AppInterceptor());
	}

}
