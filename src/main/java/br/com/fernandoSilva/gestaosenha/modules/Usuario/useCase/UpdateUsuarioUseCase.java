package br.com.fernandoSilva.gestaosenha.modules.Usuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.Usuario.dto.UsuarioAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.Usuario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.Usuario.repository.UsuarioRepository;
import br.com.fernandoSilva.gestaosenha.modules.execption.UserBadRequestExecption;
import br.com.fernandoSilva.gestaosenha.modules.execption.UserNotFoundExecption;

@Service
public class UpdateUsuarioUseCase {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario execute(UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        var usuario = this.usuarioRepository.findById(usuarioAtualizacaoDto.id())
                .orElseThrow(() -> {
                    throw new UserNotFoundExecption("id não encontrado, por favor, digite um id válido");
                });

        if (usuarioAtualizacaoDto.nome() == null || usuarioAtualizacaoDto.nome().trim().isEmpty()) {
            throw new UserBadRequestExecption("O campo (nome) não pode estar vazio");
        }

        usuario.atualizar(usuarioAtualizacaoDto);
        return this.usuarioRepository.save(usuario);

    }
}
