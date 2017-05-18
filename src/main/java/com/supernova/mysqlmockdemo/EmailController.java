package com.supernova.mysqlmockdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.supernova.mysqlmockdemo.repository.EmailReponsitory;
import com.supernova.mysqlmockdemo.repository.JdbcTemplateFactory;

@RestController
@RequestMapping("/emails")
public class EmailController {

	@Autowired
	private JdbcTemplateFactory jdbcTemplateFactory;
		
	@RequestMapping("count")
	@ResponseBody
	public MyResponse count() {
		
		EmailReponsitory emailRepository = new EmailReponsitory(jdbcTemplateFactory.getJdbcTemplate());
		
		int count = emailRepository.getCount();
		
		return new MyResponse(count);
	}
	

}
