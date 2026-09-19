package com.delegrego.api_alunos.controller;

import java.util.List;

import com.delegrego.api_alunos.entity.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delegrego.api_alunos.dto.AlunoRequest;
import com.delegrego.api_alunos.dto.AlunoResponse;
import com.delegrego.api_alunos.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	private final AlunoService service;

	public AlunoController(AlunoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<AlunoResponse>> listarAlunos() {
		List<AlunoResponse> alunosResponse =  service.listarAlunos();

		return alunosResponse != null
			 ? ResponseEntity.ok().body(service.listarAlunos())
			 : ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoResponse> obterAlunoPorId(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(service.obterAlunoPorId(id));
	}

	@PostMapping
	public ResponseEntity<AlunoResponse> cadastrarAluno(@Valid @RequestBody AlunoRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.cadastrarAluno(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponse> atualizarAluno(
			@PathVariable(name = "id") int id,
			@RequestBody @Valid AlunoRequest request
	){
		AlunoResponse response = service.obterAlunoPorId(id);
		return response != null
				? ResponseEntity.ok().body(response)
				: ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarAluno(@PathVariable(name = "id") int id){
		boolean alunoDeletado = service.deletarAluno(id);

		return alunoDeletado == true
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}

}
