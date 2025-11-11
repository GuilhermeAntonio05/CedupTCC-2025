package com.virtualgym.dev.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.AlunoEditDTO;
import com.virtualgym.dev.dto.FuncionarioDTO;
import com.virtualgym.dev.dto.FuncionarioEditDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.FuncionarioModel;
import com.virtualgym.dev.model.MensalidadeModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.FuncionarioRepository;
import com.virtualgym.dev.repository.MensalidadeRepository;
import com.virtualgym.dev.service.AlunoService;
import com.virtualgym.dev.service.FuncionarioService;
import com.virtualgym.dev.service.MensalidadeService;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/edit")
public class EditController {

	@Autowired
	AlunoRepository alunoRepository;
	@Autowired
	MensalidadeRepository mensalidadeRepository;
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@GetMapping("/aluno")
	public AlunoEditDTO buscarAlunoDTO(@RequestParam long id) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		Optional<AlunoModel> aluno = alunoService.buscarPorId(id);
		return new AlunoEditDTO(aluno.get().getNome(), aluno.get().getEmail(), aluno.get().getCpf(),
				aluno.get().getTelefone(), aluno.get().getPeso(), aluno.get().getGenero(),
				aluno.get().getData_nascimento(), aluno.get().getMensalidade().getId());
	}

	@PutMapping("/aluno")
	public void editarAlunoDTO(@RequestParam long id, @RequestBody AlunoEditDTO alunoResponse) {
		AlunoService alunoService = new AlunoService(alunoRepository);
		MensalidadeService mensalidadeService = new MensalidadeService(mensalidadeRepository);

		Optional<MensalidadeModel> m = mensalidadeService.buscarPorId(alunoResponse.mensalidade());

		MensalidadeModel mensalidade = new MensalidadeModel(m.get().getId(), m.get().getEstado());
		Optional<AlunoModel> aluno = alunoService.buscarPorId(id);

		AlunoModel alunoEditado = new AlunoModel(id, alunoResponse.nome(), alunoResponse.email(), alunoResponse.cpf(),
				alunoResponse.telefone(), alunoResponse.peso(), alunoResponse.data_nascimento(), alunoResponse.genero(),
				mensalidade, aluno.get().getData_vencimento(), aluno.get().getData_inscricao(), aluno.get().getSenha());
		alunoService.criar(alunoEditado);
	}

	@GetMapping("/funcionario")
	public FuncionarioEditDTO buscarFuncionarioDTO(@RequestParam long id) {
		FuncionarioService funcionarioService = new FuncionarioService(funcionarioRepository);
		Optional<FuncionarioModel> funcionario = funcionarioService.buscarPorId(id);
		return new FuncionarioEditDTO(funcionario.get().getNome(), funcionario.get().getEmail(),
				funcionario.get().getCpf(), funcionario.get().getTelefone(), funcionario.get().getDataNascimento(),
				funcionario.get().getGenero(), funcionario.get().getCargo(), funcionario.get().getSalario(),funcionario.get().getSenha());
	}

	@PutMapping("/funcionario")
	public void editarFuncionarioDTO(@RequestParam long id, @RequestBody FuncionarioEditDTO alunoResponse) {
		FuncionarioService funcionarioService = new FuncionarioService(funcionarioRepository);

		FuncionarioModel alunoEditado = new FuncionarioModel(id, alunoResponse.nome(), alunoResponse.email(),
				alunoResponse.cpf(), alunoResponse.telefone(), alunoResponse.dataNascimento(), alunoResponse.genero(),
				alunoResponse.cargo(), alunoResponse.salario(), alunoResponse.senha());
		
		System.out.println(alunoEditado);
		
		funcionarioService.criar(alunoEditado);
	}

}
