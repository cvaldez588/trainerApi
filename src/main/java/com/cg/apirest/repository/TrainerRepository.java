package com.cg.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.apirest.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, String>{

}
