package com.virtualgym.dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.dto.CadastroTreinoDTO;
import com.virtualgym.dev.dto.TreinoDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.AlunoTreinoModel;
import com.virtualgym.dev.model.ExerciciosModel;
import com.virtualgym.dev.model.TreinoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.AlunoTreinoRepository;
import com.virtualgym.dev.repository.ExerciciosRepository;
import com.virtualgym.dev.repository.TreinoRepository;

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
	

	public void deletarPorGrupoMuscular(AlunoRepository alunoRepository, String EMAIL, String GRUPO_MUSCULAR) {
		AlunoModel aluno = alunoRepository.findByEmail(EMAIL);
		List<AlunoTreinoModel> alunoTreinos = alunoTreinoRepository.findAllByAlunoID(aluno);
		
		for (AlunoTreinoModel alunoTreino: alunoTreinos) {
			if(alunoTreino.getTreinoID().getExercicios().getGrupoMuscular().equals(GRUPO_MUSCULAR)) {
				alunoTreinoRepository.deleteById(alunoTreino.getId());
			}
		}
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

	public void cadastroTreino(AlunoRepository alunoRepository, TreinoRepository treinoRepository,
			ExerciciosRepository exerciciosRepository, CadastroTreinoDTO cadastroTreinoDTO) {
		AlunoModel aluno = alunoRepository.findByEmail(cadastroTreinoDTO.email());
		String[] seriesArray = cadastroTreinoDTO.series().stream().toArray(String[]::new);
		String[] exerciciosArray = cadastroTreinoDTO.exercicios().stream().toArray(String[]::new);

		List<ExerciciosModel> exerciciosModels = new ArrayList<ExerciciosModel>();

		for (String exec : exerciciosArray) {
			exerciciosModels.add(exerciciosRepository.findByNome(exec));
		}

		List<String[]> serie_repeticoes = new ArrayList<>();

		for (String serie : seriesArray) {
			serie_repeticoes.add(serie.split("x"));
		}

		List<AlunoTreinoModel> alunoTreinos = alunoTreinoRepository.findAllByAlunoID(aluno);

		for (AlunoTreinoModel alunoTreino : alunoTreinos) {

			if (alunoTreino.getTreinoID().getExercicios().getGrupoMuscular()
					.equals(cadastroTreinoDTO.grupoMuscular())) {
				alunoTreinoRepository.deleteById(alunoTreino.getId());
				alunoTreinos = alunoTreinoRepository.findAllByAlunoID(aluno);
			}
		}

		for (int i = 0; i < exerciciosArray.length; i++) {
			Optional<ExerciciosModel> exercicio = exerciciosRepository.findById(exerciciosModels.get(i).getId());

			TreinoModel treino = treinoRepository.findBySerieRepeticoesExercicios(
					Integer.parseInt(serie_repeticoes.get(i)[0]), Integer.parseInt(serie_repeticoes.get(i)[1]),
					exercicio);

			if (alunoTreinos.isEmpty()) {
				AlunoTreinoModel alunoTreinoModel = new AlunoTreinoModel(aluno, treino);
				alunoTreinoRepository.save(alunoTreinoModel);
			} else {

				boolean encontrado = false;

				for (AlunoTreinoModel alunoTreino : alunoTreinos) {
					if (alunoTreino.getTreinoID().getId() == treino.getId()) {
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					AlunoTreinoModel alunoTreinoModel = new AlunoTreinoModel(aluno, treino);
					alunoTreinoRepository.save(alunoTreinoModel);
				}
			}

		}
	}

}
