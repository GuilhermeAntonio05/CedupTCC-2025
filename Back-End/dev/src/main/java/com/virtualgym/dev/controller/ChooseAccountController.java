package com.virtualgym.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.repository.AlunoRepository;

@RequestMapping("/chooseAccount")
@RestController
public class ChooseAccountController {

	@Autowired
	AlunoRepository alunoRepository;
	
	/*
	@GetMapping
	public List<AlunoModel> getContas(@ResponseBody List<AlunoLoginDTO> alunos){
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarTodos();
	}
	*/
}
