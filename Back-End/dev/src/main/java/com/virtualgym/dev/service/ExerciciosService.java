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

	public void criar(ExerciciosModel exercicio) {
		List<ExerciciosModel> exercicios = exerciciosRepository.findAll();
		boolean encontrado = false;

		for (ExerciciosModel exec : exercicios) {
			if (exec.getGrupoMuscular().equals(exercicio.getGrupoMuscular())
					&& exercicio.getNome().equals(exec.getNome())) {
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			exerciciosRepository.save(exercicio);
		} else {
			System.err.println("Duplicata encontrada!");
		}
	}

	public void deletar(ExerciciosModel exercicio) {
		exerciciosRepository.delete(exercicio);
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
	
	public List<ExerciciosModel> buscarPorNome(String nome) {
		return exerciciosRepository.findByNome(nome);
	}

	public List<String> buscarDistintosGruposMusculares() {
		return exerciciosRepository.findAllDistinctGruposMusculares();
	}

	public List<String> buscarDistintosExercicios(String exec) {
		return exerciciosRepository.findAllDistinctExercicios(exec);
	}

}
