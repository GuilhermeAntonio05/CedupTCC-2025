package com.virtualgym.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.AlunoLoginDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.service.AlunoService;

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
