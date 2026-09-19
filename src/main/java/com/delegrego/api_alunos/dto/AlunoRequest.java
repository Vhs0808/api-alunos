package com.delegrego.api_alunos.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoRequest {

	@NotBlank(message = "Nome não pode ser vazio")
	@Size(max = 50)
	private String nome;

	@NotBlank
	@Email(message = "Email não válido")
	@Size(max = 50, min = 5)
	private String email;

	@NotBlank
	@Size(max = 50)
	private String senha;

	@NotNull
	@PastOrPresent
	private LocalDate dataNascimento;

	// @Min(value = 0)
	@Max(value = 10)
	@PositiveOrZero
	private double media;

}
