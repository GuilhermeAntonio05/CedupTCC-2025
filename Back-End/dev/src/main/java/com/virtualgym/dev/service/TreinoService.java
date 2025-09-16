package com.virtualgym.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.model.TreinoModel;
import com.virtualgym.dev.repository.TreinoRepository;

@Service
public class TreinoService {
	
	TreinoRepository treinoRepository;
	
	public TreinoService(TreinoRepository treinoRepository) {
		this.treinoRepository = treinoRepository;
	}
	
	public void criar(TreinoModel treinoModel){
		treinoRepository.save(treinoModel);
	}
	
	public void deletar(TreinoModel treinoModel) {
		treinoRepository.delete(treinoModel);
	}
	
	public void deletarPorId(long id) {
		treinoRepository.deleteById(id);
	}
	
	public List<TreinoModel> buscarTodos() {
		return treinoRepository.findAll();
	}
	
	public Optional<TreinoModel> buscarPorId(long id) {
		return treinoRepository.findById(id);
	}

}
