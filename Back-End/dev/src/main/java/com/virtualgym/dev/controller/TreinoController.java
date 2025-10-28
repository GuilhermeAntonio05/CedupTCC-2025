package com.virtualgym.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.TreinoDTO;
import com.virtualgym.dev.model.AlunoTreinoModel;
import com.virtualgym.dev.model.TreinoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.AlunoTreinoRepository;
import com.virtualgym.dev.service.AlunoTreinoService;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/treino")
@RestController
public class TreinoController {

	@Autowired
	AlunoTreinoRepository alunoTreinoRepository;
	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping
	public List<TreinoDTO> coletarTreinos(@RequestParam("email") String email) {
		AlunoTreinoService  alunoTreinoService = new AlunoTreinoService(alunoTreinoRepository);
		return alunoTreinoService.buscarTreinosPorEmail(alunoRepository, email);
	}
	
	@GetMapping("/alunos")
	public List<AlunoTreinoModel> coletarAlunoETreinos(@RequestParam("email") String email) {
		AlunoTreinoService  alunoTreinoService = new AlunoTreinoService(alunoTreinoRepository);
		return alunoTreinoService.buscarPorEmail(alunoRepository, email);
	}
	
	@GetMapping("/exercicios")
	public List<TreinoModel> coletarTreinosPorGrupo(@RequestParam("email") String email,@RequestParam("grupo") String grupo) {
		AlunoTreinoService  alunoTreinoService = new AlunoTreinoService(alunoTreinoRepository);
		return alunoTreinoService.buscarTreinosPorGrupo(alunoRepository, email, grupo);
	}
}
