package com.virtualgym.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.AlunoCadastroDTO;
import com.virtualgym.dev.model.ExerciciosModel;
import com.virtualgym.dev.model.FuncionarioModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.ExerciciosRepository;
import com.virtualgym.dev.repository.FuncionarioRepository;
import com.virtualgym.dev.service.AlunoService;
import com.virtualgym.dev.service.ExerciciosService;
import com.virtualgym.dev.service.FuncionarioService;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/cadastro")
@RestController
public class CadastroController {

	@Autowired
	AlunoRepository alunoRpeository;

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	ExerciciosRepository exerciciosRepository;
	
	@PostMapping("/aluno")
	public void criarAluno(@RequestBody AlunoCadastroDTO reponseAlunoModel) {
		AlunoService alunoService = new AlunoService(alunoRpeository);
		alunoService.criar(reponseAlunoModel);
	}

	@PostMapping("/funcionario")
	public void criarFuncionario(@RequestBody FuncionarioModel responseFuncionarioModel) {
		FuncionarioService funcionarioService = new FuncionarioService(funcionarioRepository);
		System.out.println(responseFuncionarioModel);
		funcionarioService.criar(responseFuncionarioModel);
	}
	
	@PostMapping("/exercicio")
	public void criarExercicio(@RequestBody ExerciciosModel responseExercicioModel) {
		ExerciciosService exerciciosService = new ExerciciosService(exerciciosRepository);
		exerciciosService.criar(responseExercicioModel);
	}
	
}
