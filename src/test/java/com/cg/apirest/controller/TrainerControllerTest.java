package com.cg.apirest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.apirest.model.Trainer;
import com.cg.apirest.repository.TrainerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.*;  

@SpringBootTest
@AutoConfigureMockMvc
class TrainerControllerTest {
	
	@Autowired 
	private MockMvc mvc;
	
	@Autowired
	private TrainerRepository trainerRepository;
	
	
	@Test
	@DisplayName("Test should pass when trainer created successfully")
	void createTrainer_Success() throws Exception {
		Trainer trainer = new Trainer();
		trainer.setEmail("mail@gmail.com");
		trainer.setFirst_name("Name");
		trainer.setLast_name("Surname");
		trainer.setPhone("1234567");
	
		ObjectMapper mapper = new ObjectMapper();
		
		
		RequestBuilder request = MockMvcRequestBuilders.post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer));
				
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		
		JSONObject responseTrainer = new JSONObject(result.getResponse().getContentAsString());
		
		assertEquals(responseTrainer.get("id"), "trainer-id-000001");
		assertEquals(responseTrainer.get("email"), trainer.getEmail());
		assertEquals(responseTrainer.get("first_name"), trainer.getFirst_name());
		assertEquals(responseTrainer.get("last_name"), trainer.getLast_name());
		assertEquals(responseTrainer.get("phone"), trainer.getPhone());
	
	}
	
	@Test
	@DisplayName("Test should fail when wrong mail")
	void createTrainer_BadRequest_WrongEmail() throws Exception {
		Trainer trainer = new Trainer();
		trainer.setEmail("mailgmail.com");
		trainer.setFirst_name("Name");
		trainer.setLast_name("Surname");
		trainer.setPhone("1234567");
	
		ObjectMapper mapper = new ObjectMapper();
		
		
		RequestBuilder request = MockMvcRequestBuilders.post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer));
				
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(400, result.getResponse().getStatus());
		
	}
	
	@Test
	@DisplayName("Test should fail when first name not informed")
	void createTrainer_BadRequest_BlankFirstName() throws Exception {
		Trainer trainer = new Trainer();
		trainer.setEmail("mail@gmail.com");
		trainer.setLast_name("Surname");
		trainer.setPhone("1234567");
	
		ObjectMapper mapper = new ObjectMapper();
		
		
		RequestBuilder request = MockMvcRequestBuilders.post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer));
				
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(400, result.getResponse().getStatus());
		
	}
	
	@Test
	@DisplayName("Test should fail when last name not informed")
	void createTrainer_BadRequest_BlankLastName() throws Exception {
		Trainer trainer = new Trainer();
		trainer.setEmail("mail@gmail.com");
		trainer.setFirst_name("Name");
		trainer.setPhone("1234567");
	
		ObjectMapper mapper = new ObjectMapper();
		
		
		RequestBuilder request = MockMvcRequestBuilders.post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer));
				
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(400, result.getResponse().getStatus());
		
	}

	@Test
	@DisplayName("Test should fail when first phone not informed")
	void createTrainer_BadRequest_BlankPhone() throws Exception {
		Trainer trainer = new Trainer();
		trainer.setEmail("mail@gmail.com");
		trainer.setFirst_name("Name");
		trainer.setLast_name("Surname");
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		RequestBuilder request = MockMvcRequestBuilders.post("/trainers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(trainer));
				
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(400, result.getResponse().getStatus());
		
	}
	@Test
	@DisplayName("Test should pass when trainer id found")
	void getTrainerbyId_Success() throws Exception {
		
		Trainer trainer = new Trainer();
		trainer.setEmail("mail@gmail.com");
		trainer.setFirst_name("Name");
		trainer.setLast_name("Surname");
		trainer.setPhone("1234567");
		trainerRepository.save(trainer);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/trainers/trainer-id-000001"); 
		MvcResult result = mvc.perform(request).andReturn();
		JSONObject responseTrainer = new JSONObject(result.getResponse().getContentAsString());
		
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(responseTrainer.get("id"), "trainer-id-000001");
		assertEquals(responseTrainer.get("email"), trainer.getEmail());
		assertEquals(responseTrainer.get("first_name"), trainer.getFirst_name());
		assertEquals(responseTrainer.get("last_name"), trainer.getLast_name());
		assertEquals(responseTrainer.get("phone"), trainer.getPhone());

	}
	
	@Test
	@DisplayName("Test should failed when trainer id not found")
	void getTrainerbyId_NotFound() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/trainers/trainer-id-000005"); 
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(404, result.getResponse().getStatus());
		assertEquals("Trainer not found with id:trainer-id-000005", result.getResponse().getContentAsString());
	}

}
