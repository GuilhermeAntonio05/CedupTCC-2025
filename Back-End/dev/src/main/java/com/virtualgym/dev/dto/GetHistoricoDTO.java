package com.virtualgym.dev.dto;

import com.virtualgym.dev.model.TreinoModel;

public record GetHistoricoDTO(String peso, java.sql.Timestamp data, TreinoModel treino) {
}
