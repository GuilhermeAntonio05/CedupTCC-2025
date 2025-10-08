package com.virtualgym.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.LoginDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.FuncionarioModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.FuncionarioRepository;
import com.virtualgym.dev.service.AlunoService;
import com.virtualgym.dev.service.FuncionarioService;

import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@PostMapping("/aluno")
	public boolean consultarCadastradoAlunoValido(@RequestBody LoginDTO response) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.consultarCadastradoValido(response.email(), response.senha());
	}
	
	@GetMapping("/aluno")
	public LoginDTO consultarCadastroAluno(@PathParam("email") String email) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		AlunoModel alunoModel = alunoService.buscarPorEmail(email);
		return new LoginDTO(alunoModel.getEmail(),alunoModel.getSenha());
	}
	
	@PostMapping("/funcionario")
	public boolean consultarCadastradoFuncionarioValido(@RequestBody LoginDTO response) {
		FuncionarioService funcionarioService = new FuncionarioService(funcionarioRepository);
		return funcionarioService.consultarCadastradoValido(response.email(), response.senha());
	}
	
	@GetMapping("/funcionario")
	public LoginDTO consultarCadastroFuncionario(@PathParam("email") String email) {
		FuncionarioService funcionarioService= new FuncionarioService(funcionarioRepository);
		FuncionarioModel funcionarioModel = funcionarioService.buscarPorEmail(email);
		return new LoginDTO(funcionarioModel.getEmail(), funcionarioModel.getSenha());
	}
}
	