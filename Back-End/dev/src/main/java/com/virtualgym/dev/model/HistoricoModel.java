package com.virtualgym.dev.model;

import java.sql.Date;

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
	@Column(name = "Data_Treino", nullable = false)
	private Date data;
	@ManyToOne
	@JoinColumn(name = "fk_Aluno_Treino_ID")
	private AlunoTreinoModel treino;

	public HistoricoModel() {
	}

	public HistoricoModel(Long id, String peso, AlunoTreinoModel treino, Date data) {
		this.id = id;
		this.peso = peso;
		this.treino = treino;
		this.data = data;
	}

	public HistoricoModel(String peso, AlunoTreinoModel treino, Date data) {
		this.peso = peso;
		this.treino = treino;
		this.data = data;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public AlunoTreinoModel getTreino() {
		return treino;
	}

	public void setTreino(AlunoTreinoModel treino) {
		this.treino = treino;
	}

	public long getId() {
		return id;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "HistoricoModel [id=" + id + ", peso=" + peso + ", data=" + data + ", treino=" + treino + "]";
	}

}
