package com.virtualgym.dev.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;

@Service
public class DashboardService {

	private AlunoRepository alunoRepository;

	public DashboardService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	// Contagem de pagamentos do mês
	public Map<String, Long> getPagamentosDoMes() {
	    List<AlunoModel> alunos = alunoRepository.findAll();

	    return alunos.stream()
	            .filter(a -> {
	                LocalDate vencimento = a.getData_vencimento().toLocalDate();
	                return vencimento.getMonth() == vencimento.getMonth() &&
	                       vencimento.getYear() == vencimento.getYear();
	            })
	            .collect(Collectors.groupingBy(a -> a.getMensalidade().getEstado().toUpperCase(),
	                                           Collectors.counting()));
	}


	// Evolução mensal (excluindo mês atual)
	public Map<String, Long> getEvolucaoMatriculas() {
		List<AlunoModel> alunos = alunoRepository.findAll();
		LocalDate agora = LocalDate.now();

		return alunos.stream().map(a -> a.getData_vencimento().toLocalDate())
				.filter(data -> data.getMonthValue() != agora.getMonthValue() || data.getYear() != agora.getYear())
				.collect(Collectors.groupingBy(data -> data.getMonth() + "/" + data.getYear(), TreeMap::new, // mantém
																												// ordenado
						Collectors.counting()));
	}
}
