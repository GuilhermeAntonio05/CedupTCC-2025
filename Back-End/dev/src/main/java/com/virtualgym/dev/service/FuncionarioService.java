package com.virtualgym.dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.virtualgym.dev.dto.AlunoDTO;
import com.virtualgym.dev.dto.FuncionarioDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.FuncionarioModel;
import com.virtualgym.dev.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	FuncionarioRepository funcionarioRepository;

	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void criar(FuncionarioModel funcionarioModel) {
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

	public List<Boolean> consultarCadastradoValido(String email, String senha) {
		FuncionarioModel funcionario = this.buscarPorEmail(email);
		List<Boolean> login = new ArrayList<Boolean>();

		try {
			if (funcionario.getEmail().equals(email) && funcionario.getSenha().equals(senha)) {
				login.add(true);
			}else {
				login.add(false);
			}
			
			if (funcionario.getCargo().equals("admin")) {
				login.add(true);
			}else {
				login.add(false);
			}
		
		} catch (NullPointerException e) {
			System.err.println(
					"FuncionarioService.consultarCadastrado(): Usuário inesxistente/Informações nulas não podem ser tratadas");
		}

		return login;
	}

	public List<FuncionarioDTO> buscarQuantidade(int quantidade) {
		int registros = quantidade;
		int limitador = 0;
		List<FuncionarioDTO> dto = new ArrayList<FuncionarioDTO>();
		List<FuncionarioModel> FUNCIONARIOS = funcionarioRepository.findAll();

		if (FUNCIONARIOS.size() < registros) {
			limitador = registros - FUNCIONARIOS.size();
		}

		try {
			for (int i = registros - 10; i < (registros - limitador); i++) {
				FuncionarioModel funcionario = FUNCIONARIOS.get(i);

				dto.add(new FuncionarioDTO(funcionario.getId(), funcionario.getNome(), funcionario.getEmail(),
						funcionario.getCpf(), funcionario.getTelefone(), funcionario.getDataNascimento(),
						funcionario.getGenero(), funcionario.getCargo(), funcionario.getSalario()));
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println(
					"AlunoService.buscarQuantidade(): Usuário inesxistente/Informações nulas não podem ser tratadas");
			return null;
		}

		return dto;
	}

}
