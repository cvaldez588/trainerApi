package com.cg.apirest.service;

import com.cg.apirest.model.Trainer;

public interface TrainerService {
	Trainer createTrainer(Trainer trainer);
	Trainer getTrainerById(String id);
}
