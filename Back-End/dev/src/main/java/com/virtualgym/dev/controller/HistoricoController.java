package com.virtualgym.dev.controller;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtualgym.dev.dto.CadastroHistoricoDTO;
import com.virtualgym.dev.dto.GetHistoricoDTO;
import com.virtualgym.dev.model.AlunoModel;
import com.virtualgym.dev.model.AlunoTreinoModel;
import com.virtualgym.dev.model.ExerciciosModel;
import com.virtualgym.dev.model.HistoricoModel;
import com.virtualgym.dev.model.TreinoModel;
import com.virtualgym.dev.repository.AlunoRepository;
import com.virtualgym.dev.repository.AlunoTreinoRepository;
import com.virtualgym.dev.repository.ExerciciosRepository;
import com.virtualgym.dev.repository.HistoricoRepository;
import com.virtualgym.dev.repository.TreinoRepository;

@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/historico")
@RestController
public class HistoricoController {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	HistoricoRepository historicoRepository;

	@Autowired
	ExerciciosRepository exerciciosRepository;

	@Autowired
	TreinoRepository treinoRepository;
	@Autowired
	AlunoTreinoRepository alunoTreinoRepository;

	@PostMapping()
	public void salvarHistorico(@RequestBody CadastroHistoricoDTO teste) {
		List<ExerciciosModel> exercicios = exerciciosRepository.findByNome(teste.nome());
		TreinoModel treino = treinoRepository.findBySerieRepeticoesExercicios(Integer.parseInt(teste.series()),
				Integer.parseInt(teste.repeticoes()), exercicios.get(0));
		AlunoModel aluno = alunoRepository.findByEmail(teste.email());
		AlunoTreinoModel alunoTreino = alunoTreinoRepository.findByAlunoIDTreinoID(aluno, treino);
		HistoricoModel historico = new HistoricoModel(teste.media(), alunoTreino,
				Timestamp.valueOf(LocalDateTime.now()));
		historicoRepository.save(historico);
	}

	@GetMapping()
	public List<Map<String, Object>> coletarHistorico(@RequestParam String email) {
		
		AlunoModel aluno = alunoRepository.findByEmail(email);
		List<HistoricoModel> historicos = historicoRepository.findAll();
		Map<Integer, Map<String, List<GetHistoricoDTO>>> semanas = new HashMap<>();
		

		for (HistoricoModel historico : historicos) {
			if (historico.getTreino().getAlunoID().equals(aluno)) {
				int semana = historico.getData().toLocalDateTime().get(ChronoField.ALIGNED_WEEK_OF_YEAR);
				String grupoMuscular = historico.getTreino().getTreinoID().getExercicios().getGrupoMuscular();

				semanas.computeIfAbsent(semana, k -> new HashMap<>())
						.computeIfAbsent(grupoMuscular, k -> new ArrayList<>())
						.add(new GetHistoricoDTO(historico.getId(), historico.getPeso(), historico.getData(),
								historico.getTreino().getTreinoID()));
			}
		}

		List<Map<String, Object>> resultado = new ArrayList<>();

		for (Map.Entry<Integer, Map<String, List<GetHistoricoDTO>>> entrySemana : semanas.entrySet()) {
			int semana = entrySemana.getKey();
			Map<String, List<GetHistoricoDTO>> grupos = entrySemana.getValue();

			// pega uma data qualquer da semana pra formatar início e fim
			LocalDateTime exemplo = grupos.values().stream().flatMap(List::stream).findFirst().get().data()
					.toLocalDateTime();

			String inicio = exemplo.with(DayOfWeek.MONDAY).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String fim = exemplo.with(DayOfWeek.SUNDAY).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			Map<String, Object> infoSemana = new HashMap<>();
			infoSemana.put("semana", semana);
			infoSemana.put("inicio", inicio);
			infoSemana.put("fim", fim);
			infoSemana.put("gruposMusculares", grupos);

			resultado.add(infoSemana);
		}

		resultado.sort(Comparator.comparing(m -> (Integer) m.get("semana")));
		return resultado;
	}

	@GetMapping("/getDetalhes")
	public List<GetHistoricoDTO> coletarTreinos(@RequestParam("historico") String[] resq) {
		List<GetHistoricoDTO> historicoDTOs = new ArrayList<GetHistoricoDTO>();

		for (String id : resq) {
			Optional<HistoricoModel> h = historicoRepository.findById(Long.parseLong(id));
			historicoDTOs.add(new GetHistoricoDTO(h.get().getId(), h.get().getPeso(), h.get().getData(),
					h.get().getTreino().getTreinoID()));
		}

		return historicoDTOs;
	}

	@GetMapping("/getPesoGraficos")
	public List<String> coletarPeso(@RequestParam("historico") String[] resq) {
	    List<String> tst = new ArrayList<String>();

	    for (String id : resq) {

	        Optional<HistoricoModel> opt = historicoRepository.findById(Long.parseLong(id));
	        if (opt.isEmpty()) continue;

	        HistoricoModel atual = opt.get();

	        // Busca o atual + anterior
	        List<HistoricoModel> lista = historicoRepository
	                .findAllByTreino(atual.getTreino().getId(), atual.getData());

	        
	        if (!lista.isEmpty()) {
	            tst.add(lista.get(0).getPeso());
	        }else {
	        	tst.add("0");
	        }
	    }

	    return tst;
	}


}