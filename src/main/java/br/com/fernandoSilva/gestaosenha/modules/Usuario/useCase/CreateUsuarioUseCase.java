package br.com.fernandoSilva.gestaosenha.modules.usuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.usuario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.usuario.repository.UsuarioRepository;
import br.com.fernandoSilva.gestaosenha.modules.execption.BadRequestExecption;
import br.com.fernandoSilva.gestaosenha.modules.execption.FoundExistingExecption;

@Service
public class CreateUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario execute(Usuario usuario){
         this.usuarioRepository.findByNome(usuario.getNome())
        .ifPresent(user -> {
            throw new FoundExistingExecption("usuário ja existe na base de dados");
        });

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new BadRequestExecption("O campo (nome) não pode estar vazio");
        }

        return this.usuarioRepository.save(usuario);
    }
}
