package com.cg.apirest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apirest.exceptions.TrainerNotFoundException;
import com.cg.apirest.model.Trainer;
import com.cg.apirest.service.TrainerService;

@RestController
@Validated
public class TrainerController {
	
	@Autowired
	private TrainerService trainerService;

	Logger logger = LoggerFactory.getLogger(TrainerController.class);
	
	@PostMapping("/trainers")
	public ResponseEntity<?> createTrainer(@Valid @RequestBody Trainer trainer){
		try {
			Trainer trainerCreated = this.trainerService.createTrainer(trainer);
			return ResponseEntity.status(HttpStatus.OK).body(this.trainerService.createTrainer(trainerCreated));
		} catch(Exception e) {
			logger.error("Fatal error in createTrainer: " + e.getStackTrace());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}
	
	@GetMapping("/trainers/{id}")
	public ResponseEntity<?> getTrainerbyId(@PathVariable String id){

		try {
			Trainer trainer = trainerService.getTrainerById(id);
			return ResponseEntity.status(HttpStatus.OK).body(trainer);
		} catch (TrainerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			logger.error("Fatal error in createTrainer: " + e.getStackTrace());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}

	
}
