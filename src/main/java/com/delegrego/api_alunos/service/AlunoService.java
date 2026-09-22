package com.delegrego.api_alunos.service;

import java.util.ArrayList;
import java.util.List;

import com.delegrego.api_alunos.exception.AlunoNaoEncontradoException;
import com.delegrego.api_alunos.exception.EmailDuplicadoException;
import org.springframework.stereotype.Service;

import com.delegrego.api_alunos.dto.AlunoRequest;
import com.delegrego.api_alunos.dto.AlunoResponse;
import com.delegrego.api_alunos.entity.Aluno;

@Service
public class AlunoService {

	private final List<Aluno> alunos;
	private int id = 1;

	public AlunoService() {
		alunos = new ArrayList<Aluno>();
	}

	public List<AlunoResponse> listarAlunos() {

		List<AlunoResponse> alunosResponse = new ArrayList<>();

		for (Aluno a : alunos) {
			alunosResponse
					.add(new AlunoResponse(a.getId(), a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia()));
		}
		return alunosResponse;

	}

	public AlunoResponse obterAlunoPorId(int id) {
		for (Aluno a : alunos) {
			if (a.getId() == id) {
				return new AlunoResponse(
						id,
						a.getNome(),
						a.getEmail(),
						a.getDataNascimento(),
						a.getMedia());
			}
		}
		throw new AlunoNaoEncontradoException("Aluno não encontrado");
	}

	public AlunoResponse cadastrarAluno(AlunoRequest request) {

		for (Aluno aluno : alunos){
			if(aluno.getEmail().equalsIgnoreCase(request.getEmail())){
				throw new EmailDuplicadoException("Email já cadastrado");
			}
		}

		alunos.add(new Aluno(id, request.getNome(), request.getEmail(), request.getSenha(), request.getDataNascimento(),
				request.getMedia()));

		id++;

		Aluno alunoCadastrado = alunos.get(alunos.size() - 1 /* alunos.getLast() */);
		
		return new AlunoResponse(
				alunoCadastrado.getId(),
				alunoCadastrado.getNome(),
				alunoCadastrado.getEmail(),
				alunoCadastrado.getDataNascimento(),
				alunoCadastrado.getMedia());

	}

	public AlunoResponse atualizarAluno(int id, AlunoRequest request) {

		for (Aluno aluno : alunos){
			if(aluno.getEmail().equalsIgnoreCase(request.getEmail()) && id != aluno.getId()){
				throw new EmailDuplicadoException("Email já cadastrado");
			}
		}

		for(Aluno aluno : alunos){
			if(aluno.getId() == id){
				aluno.setId(id);
				aluno.setNome(request.getNome());
				aluno.setSenha(request.getSenha());
				aluno.setEmail(request.getEmail());
				aluno.setMedia(request.getMedia());
				aluno.setDataNascimento(request.getDataNascimento());

				return new AlunoResponse(
						aluno.getId(),
						aluno.getNome(),
						aluno.getEmail(),
						aluno.getDataNascimento(),
						aluno.getMedia()
				);
			}
		}

		throw new AlunoNaoEncontradoException("Aluno inexistente");
	}

	public void deletarAluno(int id) {
		for(Aluno aluno : alunos){
			if(aluno.getId() == id){
				alunos.remove(aluno);
				return;
			}
		}
		throw new RuntimeException("Aluno inexistente");
	}

}