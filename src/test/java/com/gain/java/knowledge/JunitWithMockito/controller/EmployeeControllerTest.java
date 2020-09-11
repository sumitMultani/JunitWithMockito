package com.gain.java.knowledge.JunitWithMockito.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gain.java.knowledge.JunitWithMockito.model.Employee;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wc;
	
	public static final Employee employee = Employee.builder()
			.id(1)
			.name("ravi")
			.message("Gain Java Knowledge")
			.build();
	
	List<Employee> employees = new ArrayList<Employee>();
	ObjectMapper MAPPER = new ObjectMapper();
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}
	
	@Test
	public void saveEmployee() throws JsonProcessingException, Exception{
		MvcResult result = mockMvc.perform(put("/api/employee")
				.contentType(APPLICATION_JSON_VALUE)
				.content(MAPPER.writeValueAsString(employee)))
				.andExpect(status().isOk())
				.andReturn();
		
		Employee response = MAPPER.readValue(result.getResponse().getContentAsString(), Employee.class);
	   assertEquals(employee, response);
	}
	
	@Test
	public void getEmployees() throws JsonProcessingException, Exception{
		MvcResult result = mockMvc.perform(get("/api/employee")
				.contentType(APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		List<Employee> response = MAPPER.readValue(result.getResponse()
				.getContentAsString(), new TypeReference<List<Employee>>() {
		});
		assertEquals(employees, response);
	}
	
	@Test
    public void updateEmployee() throws Exception {
        MvcResult result = mockMvc.perform(patch("/api/employee/{employee-id}", 1)
                .contentType(APPLICATION_JSON_VALUE)
                .content(MAPPER.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andReturn();
        Employee response = MAPPER.readValue(result.getResponse().getContentAsString(), Employee.class);
        assertEquals(employee, response);
    }
	
	@Test
	public void deleteEmployee() throws Exception {
		mockMvc.perform(delete("/api/employee/{employee-id}", 1)
				.contentType(APPLICATION_JSON_VALUE))
				.andExpect(status().isNoContent())
				.andReturn();
	}
}
