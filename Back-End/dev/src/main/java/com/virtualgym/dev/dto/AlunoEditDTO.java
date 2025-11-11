package com.virtualgym.dev.dto;

import java.sql.Date;

import com.virtualgym.dev.model.MensalidadeModel;

public record AlunoEditDTO(String nome, String email, String cpf, String telefone, float peso, char genero,
			Date data_nascimento, Long mensalidade) {

}
