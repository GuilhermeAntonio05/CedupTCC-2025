package com.virtualgym.dev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "treino_exercicios")
public class TreinoExerciciosModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Treino_Exercicios_ID")
	private long id;
	@ManyToOne
	@JoinColumn(name = "fk_Exercicio_ID", nullable = false)
	private ExerciciosModel exerciciosModel;
	@ManyToOne
	@JoinColumn(name = "fk_Treino_ID", nullable = false)
	private TreinoModel treinoModel;
	
	private TreinoExerciciosModel() {
	}
	
	public TreinoExerciciosModel(long id,ExerciciosModel exerciciosModel, TreinoModel treinoID) {
		this.id = id;
		this.exerciciosModel = exerciciosModel;
		this.treinoModel = treinoID;
	}
	
	public TreinoExerciciosModel(ExerciciosModel exerciciosModel, TreinoModel treinoID) {
		this.exerciciosModel = exerciciosModel;
		this.treinoModel = treinoID;
	}

	public long getId() {
		return id;
	}

	public TreinoModel getTreinoModel() {
		return treinoModel;
	}

	public void setTreinoModel(TreinoModel treinoModel) {
		this.treinoModel = treinoModel;
	}

	public void setExerciciosModel(ExerciciosModel exerciciosModel) {
		this.exerciciosModel = exerciciosModel;
	}

	public ExerciciosModel getExerciciosModel() {
		return exerciciosModel;
	}

	@Override
	public String toString() {
		return "TreinoExerciciosModel [id=" + id + ", exerciciosModel=" + exerciciosModel + ", treinoModel="
				+ treinoModel + "]";
	}

}
