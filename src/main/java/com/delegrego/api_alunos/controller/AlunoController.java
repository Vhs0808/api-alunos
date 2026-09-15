package com.delegrego.api_alunos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<AlunoResponse> listarAlunos() {
		return service.listarAlunos();
	}

	@GetMapping("/{id}")
	public AlunoResponse obterAlunoPorId(@PathVariable int id) {
		return service.obterAlunoPorId(id);
	}

	@PostMapping
	public AlunoResponse cadastrarAluno(@Valid @RequestBody AlunoRequest request) {
		return service.cadastrarAluno(request);
	}

}
