package com.virtualgym.dev.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.virtualgym.dev.dto.AlunoDTO;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.service.AlunoService;
import com.virtualgym.dev.service.DashboardService;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/home")
@RestController
public class HomeController {

	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping
	public List<AlunoDTO> buscarQuantidade(@RequestParam(value = "position") int quantidade) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		return alunoService.buscarQuantidade(quantidade);
	}

	@DeleteMapping
	public void deletarAluno(@RequestParam("id") long id) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		alunoService.deletarPorId(id);
	}

    @GetMapping("/dashboard/pagamentosMes")
    public Map<String, Long> getPagamentosDoMes() {
        DashboardService dashboardService = new DashboardService(alunoRepository);
        return dashboardService.getPagamentosDoMes();
    }

    @GetMapping("/dashboard/evolucaoMatriculas")
    public Map<String, Long> getEvolucaoMatriculas() {
        DashboardService dashboardService = new DashboardService(alunoRepository);
        return dashboardService.getEvolucaoMatriculas();
    }
}

