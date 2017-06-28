package com.supernova.mysqlmockdemo.embedded;

import org.slf4j.*;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.config.SchemaConfig.aSchemaConfig;
import static com.wix.mysql.distribution.Version.v5_7_17;

public class EmbeddedMysqlManager {
	
	private static EmbeddedMysqlManager instance;

	private EmbeddedMysqlManager() {
		startDatabase();
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmbeddedMysql mysqld;
	private SchemaConfig schemaConfig;

	// use "root"is NOT allowed.
	private final String DB_USER = "testRoot";
	private final String DB_PASSWD = "testPwd";
	
	private int dbPort = 4408;

	private String dbSchemaName="tiger_db";
	
	public void reloadSchema() {
		mysqld.reloadSchema(schemaConfig);
	}

	private void startDatabase() {
		MysqldConfig dbConfig = aMysqldConfig(v5_7_17).withCharset(UTF8).withPort(dbPort).withUser(DB_USER, DB_PASSWD)
				.build();

		schemaConfig = aSchemaConfig(dbSchemaName)
				.withScripts(classPathScript("sql/mysql/test-only/tiger_db-20170517.sql")).withCharset(UTF8).build();

		logger.info("embedded mysql starting ...");

		// start database now.
		mysqld = anEmbeddedMysql(dbConfig).addSchema(schemaConfig).start();

		logger.info("embedded mysql started.");

	}

	@Override
	public void finalize() {
		if (mysqld != null) {
			mysqld.stop();
		}
	}

	public static EmbeddedMysqlManager getInstance() {
		if (instance == null) {
			instance = new EmbeddedMysqlManager();
		}
		return instance;
	}

	String getDbUrl() {
		return "jdbc:mysql://localhost:" + dbPort + "/" + dbSchemaName;
	}

	public String getDbUser() {
		return DB_USER;
	}

	public String getDbPassword() {
		return DB_PASSWD;
	}
}
