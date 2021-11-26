package com.liuhuachao.springbootwebapi.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * AGVSController Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>11/26/2021</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AGVControllerTest {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new AGVController()).build();
	}

	/**
	 * Method: get()
	 */
	@Test
	public void testGet() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/agv/get")
				.param("json", "Hello World!")
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	/**
	 * Method: post(@RequestBody String json)
	 */
	@Test
	public void testPost() throws Exception {
		String requestBody = "{\"id\":1, \"name\":\"zhang\"}";

		mvc.perform(MockMvcRequestBuilders.post("/api/agv/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

} 
