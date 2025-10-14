package com.virtualgym.dev.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.dto.AlunoCadastroDTO;
import com.virtualgym.dev.dto.AlunoDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.MensalidadeModel;
import com.virtualgym.dev.repository.AlunoRepository;

@Service
public class AlunoService {

	AlunoRepository alunoRepository;

	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public void criar(AlunoCadastroDTO response) {
		AlunoModel aluno = new AlunoModel(response.nome(), response.email(), response.cpf(), response.telefone(),
				response.peso(), response.data_nascimento(), response.genero(), new MensalidadeModel(1L),
				Date.valueOf(LocalDate.now().plusMonths(1l)), Date.valueOf(LocalDate.now()), response.senha());
		alunoRepository.save(aluno);
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

	public List<AlunoDTO> buscarTodos() {
		List<AlunoDTO> dto = new ArrayList<AlunoDTO>();
		List<AlunoModel> ALUNOS = alunoRepository.findAll();

		for (AlunoModel aluno : ALUNOS) {
			dto.add(new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getTelefone(),
					aluno.getPeso(), aluno.getData_nascimento(), aluno.getGenero(), aluno.getMensalidade(),
					aluno.getData_nascimento()));
		}

		return dto;
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
		} catch (NullPointerException e) {
			System.err.println(
					"AlunoService.consultarCadastrado(): Usuário inesxistente/Informações nulas não podem ser tratadas");
		}

		return false;
	}

	public List<AlunoDTO> buscarQuantidade(int quantidade) {
		int registros = quantidade;
		int limitador = 0;
		List<AlunoDTO> dto = new ArrayList<AlunoDTO>();
		List<AlunoModel> ALUNOS = alunoRepository.findAll();

		if (ALUNOS.size() < registros) {
			limitador = registros - ALUNOS.size();
		}

		try {
			for (int i = registros - 10; i < (registros - limitador); i++) {
				AlunoModel aluno = ALUNOS.get(i);

				dto.add(new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf(),
						aluno.getTelefone(), aluno.getPeso(), aluno.getData_nascimento(), aluno.getGenero(),
						aluno.getMensalidade(), aluno.getData_nascimento()));
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println(
					"AlunoService.buscarQuantidade(): Usuário inesxistente/Informações nulas não podem ser tratadas");
			return null;
		}

		return dto;
	}
}
