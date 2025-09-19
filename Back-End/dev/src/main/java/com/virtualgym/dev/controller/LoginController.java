package com.virtualgym.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.AlunoLoginDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.service.AlunoService;

import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	AlunoRepository alunoRepository;

	@PostMapping()
	public boolean consultarCadastradoValido(@RequestBody AlunoLoginDTO response) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.consultarCadastradoValido(response.email(), response.senha());
	}
	
	@GetMapping()
	public AlunoLoginDTO consultarCadastro(@PathParam("email") String email) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		AlunoModel alunoModel = alunoService.buscarPorEmail(email);
		return new AlunoLoginDTO(alunoModel.getEmail(),alunoModel.getSenha());
	}
}
	