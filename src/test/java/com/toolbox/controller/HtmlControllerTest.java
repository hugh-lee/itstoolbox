package com.toolbox.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.toolbox.controller.HelloController;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class HtmlControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void makeAllHtml() throws Exception {
		String param = "{}";
		mvc.perform(MockMvcRequestBuilders.get("/html.make").content(param).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello World")));
	}

	@Test
	public void makeIndexHtml() throws Exception {
		String param = "{'name':'index'}";
		mvc.perform(MockMvcRequestBuilders.get("/html.make").content(param).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello World")));
	}
}
