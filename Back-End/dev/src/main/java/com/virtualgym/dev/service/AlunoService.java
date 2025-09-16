package com.virtualgym.dev.service;

import java.util.Iterator;
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

	public boolean consultarCadastrado(String email, String senha) {
		List<AlunoModel> lista = this.buscarTodos();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEmail().equals(email) && lista.get(i).getSenha().equals(senha)) {
				return true;
			}
		}

		return false;
	}
}
