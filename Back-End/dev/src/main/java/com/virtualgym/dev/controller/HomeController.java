package com.virtualgym.dev.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.service.AlunoService;
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/home")
@RestController
public class HomeController {
	
	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping("/{id}")
	public Optional<AlunoModel> buscarTodos(@PathVariable("id") long id) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarPorId(id);
	}
	
	@PostMapping()
	public Optional<AlunoModel> teste() {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarPorId(1);
	}

}
