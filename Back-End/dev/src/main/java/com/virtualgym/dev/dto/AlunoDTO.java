package com.virtualgym.dev.dto;

import java.sql.Date;

import com.virtualgym.dev.model.MensalidadeModel;

public record AlunoDTO(Long id, String nome, String email, String cpf, String telefone, float peso,
		Date data_nascimento, char genero, MensalidadeModel mensalidade, Date data_vencimento) {
}
