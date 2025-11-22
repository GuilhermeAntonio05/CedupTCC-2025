package com.virtualgym.dev.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercicios")
public class ExerciciosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Exercicio_ID")
	private Long id;
	@Column(name = "Grupo_Muscular", nullable = false)
	private String grupoMuscular;
	@Column(name = "Nome", nullable = false)
	private String nome;
	@Column(name = "Link_Video_Exemplo")
	private String linkVideo;
	@OneToMany(mappedBy = "exercicios", cascade = CascadeType.ALL)
	private List<TreinoModel> treino;

	public ExerciciosModel() {
	}

	public ExerciciosModel(String grupoMuscular, String nome,String linkVideo) {
		this.grupoMuscular = grupoMuscular;
		this.nome = nome;
		this.linkVideo = linkVideo;
	}

	public ExerciciosModel(Long id, String grupoMuscular, String nome,String linkVideo) {
		this.id = id;
		this.grupoMuscular = grupoMuscular;
		this.nome = nome;
		this.linkVideo = linkVideo;
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
	
	public String getLinkVideo() {
		return linkVideo;
	}
	
	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}

	@Override
	public String toString() {
		return "ExerciciosModel [id=" + id + ", grupoMuscular=" + grupoMuscular + ", nome=" + nome + ", linkVideo="
				+ linkVideo + ", treino=" + treino + "]";
	}

}
