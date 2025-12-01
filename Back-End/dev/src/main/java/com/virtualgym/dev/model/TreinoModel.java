package com.virtualgym.dev.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@JoinColumn(name = "fk_Funcionario_ID")
	private FuncionarioModel funcionario;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Exercicios_ID", nullable = false)
	private ExerciciosModel exercicios;
	
	@OneToMany(mappedBy = "treinoID",cascade = CascadeType.ALL)
	private List<AlunoTreinoModel> alunoTreinoModel;
	
	public TreinoModel() {
	}

	public TreinoModel(Long id, int serie, int repeticoes, FuncionarioModel funcionario, ExerciciosModel exercicios) {
		this.id = id;
		this.serie = serie;
		this.repeticoes = repeticoes;
		this.funcionario = funcionario;
		this.exercicios = exercicios;

	}

	public TreinoModel(int serie, int repeticoes, FuncionarioModel funcionario, ExerciciosModel exercicios) {
		this.serie = serie;
		this.repeticoes = repeticoes;
		this.funcionario = funcionario;
		this.exercicios = exercicios;
	}
	
	public TreinoModel(Long id, int serie, int repeticoes, ExerciciosModel exercicios) {
		this.id = id;
		this.serie = serie;
		this.repeticoes = repeticoes;
		this.exercicios = exercicios;

	}

	public TreinoModel(int serie, int repeticoes, ExerciciosModel exercicios) {
		this.serie = serie;
		this.repeticoes = repeticoes;
		this.exercicios = exercicios;
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
	
	public ExerciciosModel getExercicios() {
		return exercicios;
	}
	
	public void setExercicios(ExerciciosModel exercicios) {
		this.exercicios = exercicios;
	}

	@Override
	public String toString() {
		return "TreinoModel [id=" + id + ", serie=" + serie + ", repeticoes=" + repeticoes + ", funcionario="
				+ funcionario + ", exercicios=" + exercicios + "]";
	}

}
