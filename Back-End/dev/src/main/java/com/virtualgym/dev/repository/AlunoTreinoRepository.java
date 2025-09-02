package com.virtualgym.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.AlunoTreinoModel;
import com.virtualgym.dev.model.FuncionarioModel;

@Repository
public interface AlunoTreinoRepository extends JpaRepository<AlunoTreinoModel, Long>{

}
