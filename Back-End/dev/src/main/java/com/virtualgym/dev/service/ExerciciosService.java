package com.virtualgym.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.model.ExerciciosModel;
import com.virtualgym.dev.repository.ExerciciosRepository;

@Service
public class ExerciciosService {

	ExerciciosRepository exerciciosRepository;

	public ExerciciosService(ExerciciosRepository exerciciosRepository) {
		this.exerciciosRepository = exerciciosRepository;
	}

	public void criar(ExerciciosModel aluno) {
		exerciciosRepository.save(aluno);
	}

	public void deletar(ExerciciosModel aluno) {
		exerciciosRepository.delete(aluno);
	}

	public void deletarPorId(long id) {
		exerciciosRepository.deleteById(id);
	}

	public List<ExerciciosModel> buscarTodos() {
		return exerciciosRepository.findAll();
	}

	public Optional<ExerciciosModel> buscarPorId(long id) {
		return exerciciosRepository.findById(id);
	}

	public List<String> buscarDistintosGruposMusculares() {
		return exerciciosRepository.findAllDistinctGruposMusculares();
	}

	public List<String> buscarDistintosExercicios(String exec) {
		return exerciciosRepository.findAllDistinctExercicios(exec);
	}

}
