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
@Table(name = "aluno")
public class AlunoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Aluno_ID")
	private Long id;
	@Column(name = "Nome", nullable = false)
	private String nome;
	@Column(name = "Email", nullable = false)
	private String email;
	@Column(name = "CPF", nullable = false)
	private String cpf;	@Column(name = "Telefone", nullable = false)
	private String telefone;
	@Column(name = "Peso", nullable = false)
	private float peso;
	@Column(name = "Data_nascimento", nullable = false)
	private Date data_nascimento;
	@Column(name = "Genero", nullable = false)
	private String genero;
	@ManyToOne
	@JoinColumn(name = "fk_Mensalidade_ID")
	private MensalidadeModel mensalidade;
	@Column(name = "Data_Vencimento", nullable = false)
	private Date data_vencimento;

	@Override
	public String toString() {
		return "AlunoModel [id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", telefone="
				+ telefone + ", peso=" + peso + ", data_nascimento=" + data_nascimento + ", genero=" + genero
				+ ", mensalidade=" + mensalidade + ", data_vencimento=" + data_vencimento + "]";
	}

	public AlunoModel() {

	}


	public AlunoModel(Long id, String nome, String email, String cpf, String telefone, float peso, Date data_nascimento,
			String genero, MensalidadeModel mensalidade, Date data_vencimento) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.peso = peso;
		this.data_nascimento = data_nascimento;
		this.genero = genero;
		this.mensalidade = mensalidade;
		this.data_vencimento = data_vencimento;
	}

	public AlunoModel(String nome, String email, String cpf, String telefone, float peso, Date data_nascimento,
			String genero, MensalidadeModel mensalidade, Date data_vencimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.peso = peso;
		this.data_nascimento = data_nascimento;
		this.genero = genero;
		this.mensalidade = mensalidade;
		this.data_vencimento = data_vencimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public MensalidadeModel getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(MensalidadeModel mensalidade) {
		this.mensalidade = mensalidade;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

}
