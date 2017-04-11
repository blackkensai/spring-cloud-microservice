package com.foo;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan
@MapperScan(basePackages = "com.foo.persistence")
public class SpringCloudStoreServiceApplication {
	// @Bean
	// public DataSource dataSource(DataSourceProperties properties) {
	// return (HikariDataSource)
	// properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	// }

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource datasource) {
		return new DataSourceTransactionManager(datasource);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStoreServiceApplication.class, args);
	}
}
