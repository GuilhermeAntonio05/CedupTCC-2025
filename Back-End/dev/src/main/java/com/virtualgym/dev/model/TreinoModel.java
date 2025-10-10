package com.virtualgym.dev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "treino")
public class TreinoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Treino_ID")
	private Long id;
	@Column(name = "Serie", nullable = false)
	private int serie;
	@Column(name = "Repeticoes", nullable = false)
	private int repeticoes;
	@ManyToOne
	@JoinColumn(name = "fk_Funcionario_ID", nullable = true)
	private FuncionarioModel funcionario;

	public TreinoModel() {
	}

	public TreinoModel(Long id, int serie, int repeticoes, FuncionarioModel funcionario) {
		this.id = id;
		this.serie = serie;
		this.repeticoes = repeticoes;
		this.funcionario = funcionario;

	}

	public TreinoModel(int serie, int repeticoes, FuncionarioModel funcionario) {
		this.serie = serie;
		this.repeticoes = repeticoes;
		this.funcionario = funcionario;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public int getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}

	public FuncionarioModel getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioModel funcionario) {
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "TreinoModel [id=" + id + ", serie=" + serie + ", repeticoes=" + repeticoes + ", funcionario="
				+ funcionario + ", exerciciosModel=" + "]";
	}

}
