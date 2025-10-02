package com.virtualgym.dev.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class FuncionarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "Funcionario_ID")
	private Long id;
	@Column(name = "Nome", nullable = false)
	private String nome;
	@Column(name = "Email", unique = true, nullable = false)
	private String email;
	@Column(name = "CPF", unique = true, nullable = false)
	private String cpf;
	@Column(name = "Telefone", nullable = false)
	private String telefone;
	@Column(name = "Data_Nascimento", nullable = false)
	private Date dataNascimento;
	@Column(name = "Genero", nullable = false)
	private char genero;
	@Column(name = "Cargo", nullable = false)
	private String cargo;
	@Column(name = "Salario", nullable = false)
	private double salario;
	@Column(name = "Senha", nullable = false)
	private String senha;
	
	public FuncionarioModel() {
	}

	public FuncionarioModel(Long id, String nome, String email, String cpf, String telefone, Date dataNascimento,
			char genero, String cargo, double salario, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.cargo = cargo;
		this.salario = salario;
		this.senha = senha;
	}

	public FuncionarioModel(String nome, String email, String cpf, String telefone, Date dataNascimento, char genero,
			String cargo, double salario, String senha)  {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.cargo = cargo;
		this.salario = salario;
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "FuncionarioModel [id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", telefone="
				+ telefone + ", dataNascimento=" + dataNascimento + ", genero=" + genero + ", cargo=" + cargo
				+ ", salario=" + salario + ", senha=" + senha + "]";
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}
	
}
