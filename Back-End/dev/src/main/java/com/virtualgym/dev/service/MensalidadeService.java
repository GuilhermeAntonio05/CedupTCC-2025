package com.virtualgym.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.MensalidadeModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.MensalidadeRepository;

@Service
public class MensalidadeService {
	
	MensalidadeRepository mensalidadeRepository;
	
	public MensalidadeService(MensalidadeRepository mensalidadeRepository) {
		this.mensalidadeRepository = mensalidadeRepository;
	}
	
	public void criar(MensalidadeModel mensalidadeModel){
		mensalidadeRepository.save(mensalidadeModel);
	}
	
	public void deletar(MensalidadeModel mensalidadeModel) {
		mensalidadeRepository.delete(mensalidadeModel);
	}
	
	public void deletarPorId(long id) {
		mensalidadeRepository.deleteById(id);
	}
	
	public List<MensalidadeModel> buscarTodos() {
		return mensalidadeRepository.findAll();
	}
	
	public Optional<MensalidadeModel> buscarPorId(long id) {
		return mensalidadeRepository.findById(id);
	}

}
