package com.virtualgym.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.AlunoDTO;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.service.AlunoService;
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/home")
@RestController
public class HomeController {
	
	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping
	public List<AlunoDTO> buscarQuantidade(@RequestParam(value = "position") int quantidade) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarQuantidade(quantidade);
	}
}
