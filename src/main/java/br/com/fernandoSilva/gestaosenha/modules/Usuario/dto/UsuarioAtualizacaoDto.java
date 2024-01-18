package br.com.fernandoSilva.gestaosenha.modules.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioAtualizacaoDto(
        Long id,

        @NotBlank(message = "O campo [Nome] não pode ser nullo") 
        String nome) {
}
