package com.virtualgym.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.AlunoLoginDTO;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.service.AlunoService;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	AlunoRepository alunoRepository;

	@PostMapping()
	public boolean consultarCadastro(@RequestBody AlunoLoginDTO response) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.consultarCadastrado(response.email(), response.senha());
	}
}
