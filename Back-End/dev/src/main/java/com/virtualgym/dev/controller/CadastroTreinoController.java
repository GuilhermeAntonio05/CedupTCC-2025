package com.virtualgym.dev.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.repository.ExerciciosRepository;
import com.virtualgym.dev.repository.TreinoRepository;

import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/cadastro/treino")
@RestController
public class CadastroTreinoController {

	@Autowired
	TreinoRepository treinoRepository;

	@Autowired
	ExerciciosRepository exerciciosRepository;

	@GetMapping
	public List<String> coletarTreinos() {
		return exerciciosRepository.findAllDistinctGruposMusculares();
	}

	@GetMapping("treino")
	public List<String> coletarExercicos(@PathParam("grupo") String exec) {
	//	JSONObject json = new JSONObject(exec);
	//	String exec1 = json.getString("exec");
		return exerciciosRepository.findAllDistinctExercicios(exec);
	}
	
	@PostMapping
	public List<String> cadastrar(@RequestBody String exec) {
		JSONObject json = new JSONObject(exec);
		String exec1 = json.getString("exec");
		return exerciciosRepository.findAllDistinctExercicios(exec1);
	}

}
