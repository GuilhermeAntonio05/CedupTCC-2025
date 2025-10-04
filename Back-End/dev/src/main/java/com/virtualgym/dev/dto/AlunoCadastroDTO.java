package com.virtualgym.dev.dto;

import java.sql.Date;

public record AlunoCadastroDTO(String nome, String email, String cpf, String telefone, float peso,
		Date data_nascimento, char genero, String senha) {

}