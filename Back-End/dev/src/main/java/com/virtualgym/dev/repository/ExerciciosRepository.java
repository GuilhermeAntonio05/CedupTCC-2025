package com.virtualgym.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualgym.dev.model.ExerciciosModel;

@Repository
public interface ExerciciosRepository extends JpaRepository<ExerciciosModel, Long> {

}
