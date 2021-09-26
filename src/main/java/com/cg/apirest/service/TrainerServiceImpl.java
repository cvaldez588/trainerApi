package com.cg.apirest.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.apirest.exceptions.TrainerNotFoundException;
import com.cg.apirest.model.Trainer;
import com.cg.apirest.repository.TrainerRepository;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {
	
	@Autowired
	private TrainerRepository trainerRepository;
	
	@Override
	public Trainer createTrainer(Trainer trainer) {
		return trainerRepository.save(trainer);
	}
	
	@Override
	public Trainer getTrainerById(String id) {
		Optional<Trainer> trainerDb = this.trainerRepository.findById(id);
		if(trainerDb.isPresent()) {
			return trainerDb.get();
		} else {
			throw new TrainerNotFoundException("Trainer not found with id:" + id);
		}
	}
}
