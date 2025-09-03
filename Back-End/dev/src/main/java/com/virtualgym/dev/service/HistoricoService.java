package com.virtualgym.dev.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.HistoricoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.HistoricoRepository;

@Service
public class HistoricoService {
	
	HistoricoRepository historicoRepository;
	
	public HistoricoService(HistoricoRepository historicoRepository) {
		this.historicoRepository = historicoRepository;
	}
	
	public void criar(HistoricoModel historicoModel){
		historicoRepository.save(historicoModel);
	}
	
	public void deletar(HistoricoModel historicoModel) {
		historicoRepository.delete(historicoModel);
	}
	
	public void deletarPorId(long id) {
		historicoRepository.deleteById(id);
	}
	
	public List<HistoricoModel> buscarTodos() {
		return historicoRepository.findAll();
	}
	
	public HistoricoModel buscarPorId(long id) {
		return historicoRepository.getById(id);
	}

}
