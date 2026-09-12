package com.delegrego.api_alunos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.delegrego.api_alunos.dto.AlunoResponse;
import com.delegrego.api_alunos.model.Aluno;

@Service
public class AlunoService {

	private final List<Aluno> alunos;

	public AlunoService() {
		alunos = new ArrayList<Aluno>();

		alunos.add(new Aluno(1, "Joãozinho", "joao@email.com", "senha", LocalDate.of(2000, 8, 20), 8.5));
		alunos.add(new Aluno(2, "Ana", "ana@email.com", "senha", LocalDate.of(1998, 10, 25), 7));
		alunos.add(new Aluno(3, "Maria", "maria@email.com", "senha", LocalDate.of(2004, 10, 2), 9));
	}

	public List<AlunoResponse> listarAlunos() {
		List<Aluno> alunosModel = alunos;

		List<AlunoResponse> alunosResponse = new ArrayList<>();

		for (Aluno a : alunosModel) {
			alunosResponse
					.add(new AlunoResponse(a.getId(), a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia()));
		}
		return alunosResponse;

	}

	public AlunoResponse obterAlunoPorId(int id) {
		for (Aluno a : alunos) {
			if (a.getId() == id) {
				return new AlunoResponse(id, a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia());
			}
		}
		return null;
	}

}
