package com.virtualgym.dev.dto;

import java.sql.Date;

public record FuncionarioEditDTO(String nome, String email, String cpf, String telefone, Date dataNascimento,
		char genero, String cargo, double salario, String senha) {
}