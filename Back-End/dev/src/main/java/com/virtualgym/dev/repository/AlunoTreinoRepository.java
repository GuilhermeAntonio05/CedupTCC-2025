package com.virtualgym.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.AlunoTreinoModel;
import com.virtualgym.dev.model.TreinoModel;

@Repository
public interface AlunoTreinoRepository extends JpaRepository<AlunoTreinoModel, Long>{

	AlunoTreinoModel findByAlunoID(AlunoModel aluno);

	List<AlunoTreinoModel> findAllByAlunoID(AlunoModel aluno);

	@Query("SELECT altr FROM AlunoTreinoModel altr WHERE altr.treinoID = :treino AND altr.alunoID = :aluno")
	AlunoTreinoModel findByAlunoIDTreinoID(AlunoModel aluno, TreinoModel treino);
	
}
