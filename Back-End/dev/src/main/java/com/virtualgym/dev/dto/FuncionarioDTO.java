package com.virtualgym.dev.dto;

import java.sql.Date;

public record FuncionarioDTO (Long id, String nome, String email, String cpf, String telefone, Date dataNascimento,
		char genero, String cargo, double salario){

}
