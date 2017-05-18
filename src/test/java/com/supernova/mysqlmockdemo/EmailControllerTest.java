package com.supernova.mysqlmockdemo;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.supernova.mysqlmockdemo.embedded.EmbeddedMysqlManager;
import com.supernova.mysqlmockdemo.repository.JdbcTemplateFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedMysqlManager.class)
public class EmailControllerTest {

	@Autowired
	private EmbeddedMysqlManager mysqlManager;
	
	private MockMvc mockMvc;
	
	@Mock
	private JdbcTemplateFactory mockFactory;
	
	@InjectMocks
	private EmailController controller;
		
	@Before
	public void setup() {		
		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
	}

	@Test
	public void testCountEmail() throws Exception {
		when(mockFactory.getJdbcTemplate())
			.thenReturn(mysqlManager.getJdbcTemplate());
		
		mockMvc.perform(get("/emails/count"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("count", is(2)));
	}

}
