package br.com.fernandoSilva.gestaosenha.modules.funcionario.dto;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioAtualizacaoDto(
        Long id,

        @NotBlank(message = "O campo [Nome] não pode ser nullo") 
        String nome) {
}
