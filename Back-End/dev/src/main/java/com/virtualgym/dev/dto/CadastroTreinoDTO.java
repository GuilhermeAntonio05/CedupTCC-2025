package com.virtualgym.dev.dto;

import java.util.List;

public record CadastroTreinoDTO(String grupoMuscular,String email, List<String> exercicios, List<String> series) {

}
