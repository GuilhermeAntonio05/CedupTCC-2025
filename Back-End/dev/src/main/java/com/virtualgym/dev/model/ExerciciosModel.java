package com.virtualgym.dev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercicios")
public class ExerciciosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Exercicio_ID")
	private Long id;
	@Column(name = "Grupo_Muscular", nullable = false)
	private String grupoMuscular;
	@Column(name = "Nome", nullable = false)
	private String nome;

	public ExerciciosModel() {
	}

	public ExerciciosModel(String grupoMuscular, String nome) {
		super();
		this.grupoMuscular = grupoMuscular;
		this.nome = nome;
	}

	public ExerciciosModel(Long id, String grupoMuscular, String nome) {
		super();
		this.id = id;
		this.grupoMuscular = grupoMuscular;
		this.nome = nome;
	}

	public String getGrupoMuscular() {
		return grupoMuscular;
	}

	public void setGrupoMuscular(String grupoMuscular) {
		this.grupoMuscular = grupoMuscular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ExerciciosModel [id=" + id + ", grupoMuscular=" + grupoMuscular + ", nome=" + nome + ", treinoModel="
				+ "]";
	}

}
