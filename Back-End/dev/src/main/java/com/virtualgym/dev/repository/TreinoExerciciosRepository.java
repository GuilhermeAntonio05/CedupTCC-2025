package com.virtualgym.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.FuncionarioModel;
import com.virtualgym.dev.model.TreinoExerciciosModel;

@Repository
public interface TreinoExerciciosRepository extends JpaRepository<TreinoExerciciosModel, Long>{

}
