package com.virtualgym.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.service.AlunoService;

import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/chooseAccount")
@RestController
public class ChooseAccountController {

	@Autowired
	AlunoRepository alunoRepository;
	
	@GetMapping()
	public String retornarNome(@PathParam("email") String email) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		AlunoModel alunoModel = alunoService.buscarPorEmail(email);
		return alunoModel.getNome();
	}
}
