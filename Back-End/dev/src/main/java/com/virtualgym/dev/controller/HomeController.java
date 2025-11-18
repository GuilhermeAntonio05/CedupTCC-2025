package com.virtualgym.dev.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.virtualgym.dev.dto.AlunoDTO;
import com.virtualgym.dev.dto.FuncionarioDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.FuncionarioRepository;
import com.virtualgym.dev.service.AlunoService;
import com.virtualgym.dev.service.DashboardService;
import com.virtualgym.dev.service.FuncionarioService;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/home")
@RestController
public class HomeController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/aluno/email")
	public AlunoModel buscarAlunoPorEmail(@RequestParam(value = "email") String email) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarPorEmail(email);
	}
	
	@GetMapping("/aluno")
	public List<AlunoDTO> buscarQuantidadeAluno(@RequestParam(value = "position") int quantidade) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarQuantidade(quantidade);
	}
	
	@GetMapping("/funcionario")
	public List<FuncionarioDTO> buscarQuantidadeProfessor(@RequestParam(value = "position") int quantidade) {
		FuncionarioService funcionarioService = new FuncionarioService(funcionarioRepository);
		return funcionarioService.buscarQuantidade(quantidade);
	}

	@DeleteMapping("/aluno")
	public void deletarAluno(@RequestParam("id") long id) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		alunoService.deletarPorId(id);
	}
	
	@DeleteMapping("/funcionario")
	public void deletarFuncionario(@RequestParam("id") long id) {
		FuncionarioService funcionarioService= new FuncionarioService(funcionarioRepository);
		funcionarioService.deletarPorId(id);
	}

    @GetMapping("/dashboard/pagamentosMes")
    public Map<String, Long> getPagamentosDoMes() {
        DashboardService dashboardService = new DashboardService(alunoRepository);
        return dashboardService.getPagamentosDoMes();
    }
    
    @GetMapping("/dashboard/valorPagamentosMes")
    public Map<Object, Double> getValorPagamentosDoMes() {
        DashboardService dashboardService = new DashboardService(alunoRepository);
        return dashboardService.getValorPagamentosDoMes();
    }

    @GetMapping("/dashboard/evolucaoMatriculas")
    public Map<String, Long> getEvolucaoMatriculas() {
        DashboardService dashboardService = new DashboardService(alunoRepository);
        return dashboardService.getEvolucaoMatriculas();
    }
}

