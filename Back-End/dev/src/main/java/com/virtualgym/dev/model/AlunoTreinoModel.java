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
@Table(name = "aluno_treino")
public class AlunoTreinoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Aluno_Treino_ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "fk_Aluno_ID")
	private AlunoModel alunoID;
	@ManyToOne
	@JoinColumn(name = "fk_Treino_ID")
	private TreinoModel treinoID;

	public AlunoTreinoModel() {
	}

	public AlunoTreinoModel(long id, AlunoModel alunoID, TreinoModel treinoID) {
		this.id = id;
		this.alunoID = alunoID;
		this.treinoID = treinoID;
	}

	public AlunoTreinoModel(AlunoModel alunoID, TreinoModel treinoID) {
		this.alunoID = alunoID;
		this.treinoID = treinoID;
	}

	public AlunoModel getAlunoID() {
		return alunoID;
	}

	public void setAlunoID(AlunoModel alunoID) {
		this.alunoID = alunoID;
	}

	public TreinoModel getTreinoID() {
		return treinoID;
	}

	public void setTreinoID(TreinoModel treinoID) {
		this.treinoID = treinoID;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "AlunoTreinoModel [id=" + id + ", alunoID=" + alunoID + ", treinoID=" + treinoID + "]";
	}

}
