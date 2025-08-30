package com.virtualgym.dev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensalidade")
public class MensalidadeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Mensalidade_ID")
	private Long id;
	@Column(name = "Estado", nullable = false)
	private String estado;

	@Override
	public String toString() {
		return "MensalidadeModel [id: " + id + ", estado: " + estado + " ]";
	}

	public MensalidadeModel(Long id, String estado) {
		this.id = id;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
