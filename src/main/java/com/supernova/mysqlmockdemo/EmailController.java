package com.supernova.mysqlmockdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.supernova.mysqlmockdemo.repository.EmailRepository;

@RestController
@RequestMapping("/emails")
@EnableJpaRepositories(basePackages = "com.supernova.mysqlmockdemo")
public class EmailController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	EmailRepository emailRepository;
	
	@RequestMapping("count")
	@ResponseBody
	public MyResponse count() {
		logger.info("/emails/count called.");
		
		long count = emailRepository.count();
		
		return new MyResponse(count);
	}
	

}
