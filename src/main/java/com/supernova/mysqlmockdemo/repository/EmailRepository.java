package com.supernova.mysqlmockdemo.repository;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailRepository {	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int count() {
		if (jdbcTemplate == null) {
			logger.error("jdbcTemplate autowire failed...");
		}
		final String CNT_QUERY = "select count(*) from tiger_db.t_emails";
		Number count = jdbcTemplate.queryForObject(CNT_QUERY, Integer.class);
		return (count != null ? count.intValue() : 0);
	}	
}
