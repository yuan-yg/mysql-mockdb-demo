package com.supernova.mysqlmockdemo.embedded;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.config.SchemaConfig.aSchemaConfig;
import static com.wix.mysql.distribution.Version.v5_7_17;


@Configuration
public class EmbeddedMysqlManager implements DisposableBean {

	private JdbcTemplate jdbcTemplate;
	private EmbeddedMysql mysqld;
	private SchemaConfig schemaConfig;

	// use "root"is NOT allowed.
	private final String DB_USER = "testRoot";
	private final String DB_PASSWD = "testPwd";
	private final int DB_PORT = 4408;
	
	private final String DB_SCHEMA_NAME = "tiger_db";
	
	@PostConstruct
	public void init() {
		
		startDatabase();

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:" + DB_PORT + "/" + DB_SCHEMA_NAME);
		dataSource.setUser(DB_USER);
		dataSource.setPassword(DB_PASSWD);
		
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void reloadSchema() {
		mysqld.reloadSchema(schemaConfig);
	}
	
	private void startDatabase() {
		MysqldConfig dbConfig = aMysqldConfig(v5_7_17)
			    .withCharset(UTF8)
			    .withPort(DB_PORT)
			    .withUser(DB_USER, DB_PASSWD)
			    .build();
		
		schemaConfig = aSchemaConfig(DB_SCHEMA_NAME)
			    .withScripts(classPathScript("sql/mysql/test-only/tiger_db-20170517.sql"))
			    .withCharset(UTF8)
			    .build();
		
		// start database now.
		mysqld = anEmbeddedMysql(dbConfig)
			    .addSchema(schemaConfig)
			    .start();

	}
	
	@Override
	public void destroy() throws Exception {
		if (mysqld != null) {
			mysqld.stop();
		}
	}
}
