package com.virtualgym.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.service.AlunoService;

@RequestMapping(name = "/")
@RestController
public class MainController {
	
	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping
	public List<AlunoModel> buscarTodos() {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarTodos();
	}

	@PostMapping
	public AlunoModel teste() {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarPorId(1);
	}
}
