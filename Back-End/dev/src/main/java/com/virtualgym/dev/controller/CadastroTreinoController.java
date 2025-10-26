package com.virtualgym.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.CadastroTreinoDTO;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.AlunoTreinoRepository;
import com.virtualgym.dev.repository.ExerciciosRepository;
import com.virtualgym.dev.repository.TreinoRepository;
import com.virtualgym.dev.service.AlunoTreinoService;
import com.virtualgym.dev.service.ExerciciosService;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/cadastro/treino")
@RestController
public class CadastroTreinoController {

	@Autowired
	TreinoRepository treinoRepository;

	@Autowired
	ExerciciosRepository exerciciosRepository;

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	AlunoTreinoRepository alunoTreinoRepository;

	@GetMapping
	public List<String> coletarTreinos() {
		ExerciciosService exerciciosService = new ExerciciosService(exerciciosRepository);
		return exerciciosService.buscarDistintosGruposMusculares();
	}

	@GetMapping("/exercicios")
	public List<String> coletarExercicos(@RequestParam("grupo") String grupoMuscular) {
		ExerciciosService exerciciosService = new ExerciciosService(exerciciosRepository);
		return exerciciosService.buscarDistintosExercicios(grupoMuscular);
	}

	@PostMapping
	public void cadastroTreino(@RequestBody CadastroTreinoDTO cadastroTreinoDTO) {
		AlunoTreinoService alunoTreinoService = new AlunoTreinoService(alunoTreinoRepository);
		alunoTreinoService.cadastroTreino(alunoRepository, treinoRepository, exerciciosRepository, cadastroTreinoDTO);
	}
	
	@DeleteMapping
	public void deletarTreino(@RequestParam("grupo") String grupoMuscular, @RequestParam("email") String email) {
		AlunoTreinoService alunoTreinoService = new AlunoTreinoService(alunoTreinoRepository);
		alunoTreinoService.deletarPorGrupoMuscular(alunoRepository, email, grupoMuscular);
	}

}
