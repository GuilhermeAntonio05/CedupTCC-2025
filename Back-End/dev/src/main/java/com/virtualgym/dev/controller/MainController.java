package com.virtualgym.dev.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.model.FuncionarioModel;
import com.virtualgym.dev.repository.FuncionarioRepository;

@RequestMapping(name = "/")
@RestController
public class MainController {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public String hello() {
		return "Hello World ";
	}
	
	@PostMapping
	public FuncionarioModel teste() {
		FuncionarioModel funcionario = new FuncionarioModel("jeca","jeca2@gmail.com","14113149966","+55 (47) 9 9766-0815",Date.valueOf(LocalDate.now()),'m',"gerente",2000.00);
		funcionarioRepository.save(funcionario);
		return  funcionario;
	}
}
