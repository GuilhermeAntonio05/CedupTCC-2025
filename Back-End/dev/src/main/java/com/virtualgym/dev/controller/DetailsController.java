package com.virtualgym.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.ExerciciosRepository;
import com.virtualgym.dev.repository.TreinoExerciciosRepository;
import com.virtualgym.dev.repository.TreinoRepository;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/details")
public class DetailsController {

	@Autowired
	AlunoRepository alunoRepository;
	@Autowired
	TreinoRepository treinoRepository;
	@Autowired
	ExerciciosRepository exerciciosRepository;
	@Autowired
	TreinoExerciciosRepository treinoExerciciosRepository;
	
	@GetMapping
	public String enviarTreinos(@RequestParam("id") Long id) {
		return "";
	}
	
}
