package com.virtualgym.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.TreinoModel;

@Repository
public interface TreinoRepository extends JpaRepository<TreinoModel, Long> {

}
