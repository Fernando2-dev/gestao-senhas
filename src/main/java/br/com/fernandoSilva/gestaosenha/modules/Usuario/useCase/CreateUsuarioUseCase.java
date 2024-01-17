package br.com.fernandoSilva.gestaosenha.modules.Usuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.Usuario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.Usuario.repository.UsuarioRepository;
import br.com.fernandoSilva.gestaosenha.modules.execption.UserBadRequestExecption;
import br.com.fernandoSilva.gestaosenha.modules.execption.UserFoundExistingExecption;

@Service
public class CreateUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario execute(Usuario usuario){
         this.usuarioRepository.findByNome(usuario.getNome())
        .ifPresent(user -> {
            throw new UserFoundExistingExecption("usuário ja existe na base de dados");
        });

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new UserBadRequestExecption("O campo (nome) não pode estar vazio");
        }

        return this.usuarioRepository.save(usuario);
    }
}
