package com.virtualgym.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.FuncionarioModel;
import com.virtualgym.dev.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	FuncionarioRepository funcionarioRepository;
	
	public FuncionarioService(	FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void criar(FuncionarioModel funcionarioModel){
		try {
			funcionarioRepository.save(funcionarioModel);
		} catch (DataIntegrityViolationException e) {
			System.err.println("FuncionarioService.criar(): Não é possivel inserir informações duplicadas no banco.");
		}
	}
	
	public void deletar(FuncionarioModel funcionarioModel) {
		funcionarioRepository.delete(funcionarioModel);
	}
	
	public void deletarPorId(long id) {
		funcionarioRepository.deleteById(id);
	}
	
	public List<FuncionarioModel> buscarTodos() {
		return funcionarioRepository.findAll();
	}
	
	public Optional<FuncionarioModel> buscarPorId(long id) {
		return funcionarioRepository.findById(id);
	}

	public FuncionarioModel buscarPorEmail(String email) {
		return funcionarioRepository.findByEmail(email);
	}
	
	public boolean consultarCadastradoValido(String email, String senha) {
		FuncionarioModel funcionario = this.buscarPorEmail(email);

		try {
			if (funcionario.getEmail().equals(email) && funcionario.getSenha().equals(senha)) {
				return true;
			}
		} catch (NullPointerException e) {
			System.err.println(
					"AlunoService.consultarCadastrado(): Usuário inesxistente/Informações nulas não podem ser tratadas");
		}

		return false;
	}

}
