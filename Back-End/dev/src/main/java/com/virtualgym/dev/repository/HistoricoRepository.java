package com.virtualgym.dev.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.HistoricoModel;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoModel, Long> {

	@Query("SELECT h FROM HistoricoModel h WHERE h.treino.id = :id AND :data > data ORDER BY h.data DESC LIMIT 1")
	List<HistoricoModel> findAllByTreino(long id, Timestamp data);
}
