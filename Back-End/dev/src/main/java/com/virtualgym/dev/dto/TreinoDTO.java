package com.virtualgym.dev.dto;

import com.virtualgym.dev.model.ExerciciosModel;
import com.virtualgym.dev.model.FuncionarioModel;

public record TreinoDTO(int serie, int repeticoes, FuncionarioModel funcionario, ExerciciosModel exercicios) {

}