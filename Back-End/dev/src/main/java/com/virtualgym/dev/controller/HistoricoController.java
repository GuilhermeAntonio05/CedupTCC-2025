package com.virtualgym.dev.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.HistoricoDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.AlunoTreinoModel;
import com.virtualgym.dev.model.ExerciciosModel;
import com.virtualgym.dev.model.HistoricoModel;
import com.virtualgym.dev.model.TreinoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.AlunoTreinoRepository;
import com.virtualgym.dev.repository.ExerciciosRepository;
import com.virtualgym.dev.repository.HistoricoRepository;
import com.virtualgym.dev.repository.TreinoRepository;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/historico")
@RestController
public class HistoricoController {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	HistoricoRepository historicoRepository;

	@Autowired
	ExerciciosRepository exerciciosRepository;

	@Autowired
	TreinoRepository treinoRepository;
	@Autowired
	AlunoTreinoRepository alunoTreinoRepository;

	@PostMapping()
	public void criarAluno(@RequestBody HistoricoDTO teste) {
		ExerciciosModel exercicios = exerciciosRepository.findByNome(teste.nome());
		TreinoModel treino = treinoRepository.findBySerieRepeticoesExercicios(Integer.parseInt(teste.series()),
				Integer.parseInt(teste.repeticoes()), exercicios);
		AlunoModel aluno = alunoRepository.findByEmail(teste.email());
		AlunoTreinoModel alunoTreino = alunoTreinoRepository.findByAlunoIDTreinoID(aluno, treino);
		HistoricoModel historico = new HistoricoModel(teste.media(), alunoTreino, Date.valueOf(LocalDate.now()));
		historicoRepository.save(historico);
	}
}
