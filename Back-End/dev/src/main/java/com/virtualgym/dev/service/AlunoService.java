package com.virtualgym.dev.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;

@Service
public class AlunoService {
	
	AlunoRepository alunoRepository;
	
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	public void criar(AlunoModel aluno){
		alunoRepository.save(aluno);
	}
	
	public void deletar(AlunoModel aluno) {
		alunoRepository.delete(aluno);
	}
	
	public void deletarPorId(long id) {
		alunoRepository.deleteById(id);
	}
	
	public List<AlunoModel> buscarTodos() {
		return alunoRepository.findAll();
	}
	
	public AlunoModel buscarPorId(long id) {
		return alunoRepository.getById(id);
	}

}
