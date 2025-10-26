package com.virtualgym.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.ExerciciosModel;
import com.virtualgym.dev.model.TreinoModel;

@Repository
public interface TreinoRepository extends JpaRepository<TreinoModel, Long> {

	@Query("Select t from TreinoModel as t where serie = :ser and repeticoes = :rep and exercicios = :exec")
	TreinoModel findBySerieRepeticoesExercicios(int ser, int rep, Optional<ExerciciosModel> exec);

}
