package com.cg.apirest.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.apirest.exceptions.TrainerNotFoundException;
import com.cg.apirest.model.Trainer;
import com.cg.apirest.repository.TrainerRepository;

@SpringBootTest
public class TrainerServiceImplTest {

	
	@InjectMocks
	TrainerServiceImpl trainerService;
	
	@Mock
	TrainerRepository trainerRepository;
	
	@Test
	@DisplayName("Test should pass when trainer created")
	public void createTrainer_success() {
		Trainer trainer = new Trainer();
		trainer.setId("trainer-id-000001");
		trainer.setEmail("mail@gmail.com");
		trainer.setFirst_name("Name");
		trainer.setLast_name("Surname");
		trainer.setPhone("1234567");

		when(trainerRepository.save(trainer)).thenReturn(trainer);
		
		Trainer created = trainerService.createTrainer(trainer);
		
		assertEquals(created.getId(), trainer.getId());
		assertEquals(created.getEmail(), trainer.getEmail());
		assertEquals(created.getFirst_name(), trainer.getFirst_name());
		assertEquals(created.getLast_name(), trainer.getLast_name());
		assertEquals(created.getPhone(), trainer.getPhone());

	}	
	@Test
	@DisplayName("Test should pass when trainer id found")
	public void getTrainerById_success() {
		// Pending to be completed
	}	
	@Test
	@DisplayName("Test should fails when trainer not found")
	public void getTrainerById_notFound() {
		Trainer trainer = new Trainer();
		trainer.setEmail("mail@gmail.com");
		trainer.setFirst_name("Name");
		trainer.setLast_name("Surname");
		trainer.setPhone("1234567");
		
		when(trainerRepository.getById("trainer-id-000005")).thenReturn(trainer);
		try {
			trainerService.getTrainerById("trainer-id-000005");
			assertFalse(true);
		} catch(TrainerNotFoundException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}	
}
