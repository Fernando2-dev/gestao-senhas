package br.com.fernandoSilva.gestaosenha.modules.senha.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SenhaAtualizacaoDto(

@NotNull(message = "O campo [Id] não pode ser nullo")
Long id, 
@NotBlank(message = "O campo [Descrição] não pode ser nullo")
String descricao, 
@NotBlank(message = "O campo [Tag] não pode ser nullo")
String tag, 
@NotBlank(message = "O campo [Senha] não pode ser nullo")
String senha) {}
