package com.virtualgym.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.ExerciciosModel;

@Repository
public interface ExerciciosRepository extends JpaRepository<ExerciciosModel, Long> {
	@Query("SELECT DISTINCT e.grupoMuscular FROM ExerciciosModel e")
	List<String> findAllDistinctGruposMusculares();

	@Query("SELECT e.nome FROM ExerciciosModel e WHERE e.grupoMuscular = :exec")
	List<String> findAllDistinctExercicios(@Param("exec") String exec);

	ExerciciosModel findByNome(String string);
	
	ExerciciosModel findByGrupoMuscular(String string);

}
