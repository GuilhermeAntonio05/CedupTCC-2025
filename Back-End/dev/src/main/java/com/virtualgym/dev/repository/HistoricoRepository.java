package com.virtualgym.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.HistoricoModel;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoModel, Long> {

}
