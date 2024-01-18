package br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.dto.UsuarioAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.repository.UsuarioRepository;

@Service
public class UpdateUsuarioUseCase {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario execute(UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        var usuario = this.usuarioRepository.findById(usuarioAtualizacaoDto.id())
                .orElseThrow(() -> {
                    throw new NotFoundExecption("id não encontrado, por favor, digite um id válido");
                });

        usuario.atualizar(usuarioAtualizacaoDto);
        return this.usuarioRepository.save(usuario);

    }
}
