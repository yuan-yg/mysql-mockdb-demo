package com.supernova.mysqlmockdemo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.supernova.mysqlmockdemo.embedded.EmbeddedMysqlManager;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = {"com.supernova.mysqlmockdemo"})
public class EmailControllerTest {

	@Autowired
	private EmbeddedMysqlManager mysqlManager;
	
	@Autowired
	private TestRestTemplate restTemplate;
		
	@Before
	public void setup() {		
		// this is to show that we CAN reset db before each test.
		mysqlManager.reloadSchema();
	}

	@Test
	public void testCountEmail() throws Exception {
		ResponseEntity<MyResponse> entity = restTemplate.getForEntity("/emails/count",  MyResponse.class);
		
		assertThat(entity.getStatusCode().is2xxSuccessful());
		
		MyResponse response = entity.getBody();
		assertThat(response.getCount()).isEqualTo(2);
	}

}
