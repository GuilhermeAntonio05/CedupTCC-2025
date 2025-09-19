package com.virtualgym.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;

@Service
public class AlunoService {

	AlunoRepository alunoRepository;

	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public void criar(AlunoModel aluno) {
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

	public Optional<AlunoModel> buscarPorId(long id) {
		return alunoRepository.findById(id);
	}

	public AlunoModel buscarPorEmail(String email) {
		return alunoRepository.findByEmail(email);
	}

	public boolean consultarCadastradoValido(String email, String senha) {
		AlunoModel aluno = this.buscarPorEmail(email);
		
		try {
			if (aluno.getEmail().equals(email) && aluno.getSenha().equals(senha)) {
				return true;
			}	
		}catch (NullPointerException e){
			System.err.println("AlunoModel.consultarCadastrado(): Usuário inesxistente/Informações nulas não podem ser tratadas");
		}

		return false;
	}
}
