package com.virtualgym.dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.dto.TreinoDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.AlunoTreinoModel;
import com.virtualgym.dev.model.TreinoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.AlunoTreinoRepository;

@Service
public class AlunoTreinoService {

	AlunoTreinoRepository alunoTreinoRepository;

	public AlunoTreinoService(AlunoTreinoRepository alunoTreinoRepository) {
		this.alunoTreinoRepository = alunoTreinoRepository;
	}

	public void criar(AlunoModel aluno, TreinoModel treino) {
		AlunoTreinoModel alunoTreinoModel = new AlunoTreinoModel(aluno, treino);
		alunoTreinoRepository.save(alunoTreinoModel);
	}

	public void deletar(AlunoTreinoModel alunoTreinoModel) {
		alunoTreinoRepository.delete(alunoTreinoModel);
	}

	public void deletarPorId(long id) {
		alunoTreinoRepository.deleteById(id);
	}

	public List<AlunoTreinoModel> buscarTodos() {
		return alunoTreinoRepository.findAll();
	}

	public Optional<AlunoTreinoModel> buscarPorId(long id) {
		return alunoTreinoRepository.findById(id);
	}

	public List<AlunoTreinoModel> buscarPorEmail(AlunoRepository alunoRepository, String email) {
		AlunoModel aluno = new AlunoService(alunoRepository).buscarPorEmail(email);
		return alunoTreinoRepository.findAllByAlunoID(aluno);
	}

	public List<TreinoDTO> buscarTreinosPorEmail(AlunoRepository alunoRepository, String email) {
		AlunoModel aluno = new AlunoService(alunoRepository).buscarPorEmail(email);
		List<AlunoTreinoModel> treinos = alunoTreinoRepository.findAllByAlunoID(aluno);
		List<TreinoDTO> dto = new ArrayList<TreinoDTO>();

		try {
			for (AlunoTreinoModel alunoTreinoModel : treinos) {
				dto.add(new TreinoDTO(alunoTreinoModel.getTreinoID().getSerie(),
						alunoTreinoModel.getTreinoID().getRepeticoes(), alunoTreinoModel.getTreinoID().getFuncionario(),
						alunoTreinoModel.getTreinoID().getExercicios()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return dto;
	}

}
