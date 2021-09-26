package com.cg.apirest.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class TrainerNotFoundException extends RuntimeException {
	public TrainerNotFoundException(String message) {
		super(message);
	}

}
