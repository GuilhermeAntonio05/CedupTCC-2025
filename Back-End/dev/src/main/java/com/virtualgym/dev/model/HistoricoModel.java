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
@Table(name = "historico")
public class HistoricoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Historico_ID")
	private Long id;
	@Column(name = "Peso", nullable = false)
	private String peso;
	@ManyToOne
	@JoinColumn(name = "fk_Treino_ID", nullable = false)
	private TreinoModel treino;

	public HistoricoModel() {
	}

	public HistoricoModel(Long id, String peso, TreinoModel treino) {
		this.id = id;
		this.peso = peso;
		this.treino = treino;
	}

	public HistoricoModel(String peso, TreinoModel treino) {
		this.peso = peso;
		this.treino = treino;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public TreinoModel getTreino() {
		return treino;
	}

	public void setTreino(TreinoModel treino) {
		this.treino = treino;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "HistoricoModel [id=" + id + ", peso=" + peso + ", treino=" + treino + "]";
	}

}
