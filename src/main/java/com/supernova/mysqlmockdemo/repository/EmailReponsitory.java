package com.supernova.mysqlmockdemo.repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class EmailReponsitory {

	private JdbcTemplate jdbcTemplate;
	
	public EmailReponsitory(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int getCount() {
		final String CNT_QUERY = "select count(*) from tiger_db.t_emails";
		Number count = jdbcTemplate.queryForObject(CNT_QUERY, Integer.class);
		return (count != null ? count.intValue() : 0);
	}	
}
