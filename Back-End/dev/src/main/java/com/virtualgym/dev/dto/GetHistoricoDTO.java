package com.virtualgym.dev.dto;

import java.sql.Timestamp;

import com.virtualgym.dev.model.AlunoTreinoModel;
import com.virtualgym.dev.model.TreinoModel;

public record GetHistoricoDTO(Long id,String peso, Timestamp data, TreinoModel treino) {


}
