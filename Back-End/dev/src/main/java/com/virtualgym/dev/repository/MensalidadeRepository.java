package com.virtualgym.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.MensalidadeModel;

@Repository
public interface MensalidadeRepository extends JpaRepository<MensalidadeModel, Long>{
}
