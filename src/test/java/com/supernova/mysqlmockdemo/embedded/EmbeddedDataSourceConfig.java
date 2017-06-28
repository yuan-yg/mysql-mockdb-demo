package com.supernova.mysqlmockdemo.embedded;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
public class EmbeddedDataSourceConfig {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private MysqlDataSource dataSource;
	
	@Bean
	public DataSource getDataSource() {
		if (dataSource == null) {
			logger.info("getDataSource create bean...");
			
			EmbeddedMysqlManager mysqlManager = EmbeddedMysqlManager.getInstance();
			
			dataSource = new MysqlDataSource();
			dataSource.setUrl(mysqlManager.getDbUrl());
			dataSource.setUser(mysqlManager.getDbUser());
			dataSource.setPassword(mysqlManager.getDbPassword());
		}
		return dataSource;
	}

}
