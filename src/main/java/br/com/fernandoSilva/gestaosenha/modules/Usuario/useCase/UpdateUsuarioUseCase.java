package br.com.fernandoSilva.gestaosenha.modules.usuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.usuario.dto.UsuarioAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.usuario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.usuario.repository.UsuarioRepository;
import br.com.fernandoSilva.gestaosenha.modules.execption.BadRequestExecption;
import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;

@Service
public class UpdateUsuarioUseCase {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario execute(UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        var usuario = this.usuarioRepository.findById(usuarioAtualizacaoDto.id())
                .orElseThrow(() -> {
                    throw new NotFoundExecption("id não encontrado, por favor, digite um id válido");
                });

        if (usuarioAtualizacaoDto.nome() == null || usuarioAtualizacaoDto.nome().trim().isEmpty()) {
            throw new BadRequestExecption("O campo (nome) não pode estar vazio");
        }

        usuario.atualizar(usuarioAtualizacaoDto);
        return this.usuarioRepository.save(usuario);

    }
}
