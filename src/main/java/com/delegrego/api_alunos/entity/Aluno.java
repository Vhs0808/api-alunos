package com.delegrego.api_alunos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Aluno {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private double media;

}
