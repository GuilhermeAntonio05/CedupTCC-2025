package com.virtualgym.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.AlunoTreinoModel;

@Repository
public interface AlunoTreinoRepository extends JpaRepository<AlunoTreinoModel, Long>{

	AlunoTreinoModel findByAlunoID(AlunoModel aluno);

	List<AlunoTreinoModel> findAllByAlunoID(AlunoModel aluno);

}
