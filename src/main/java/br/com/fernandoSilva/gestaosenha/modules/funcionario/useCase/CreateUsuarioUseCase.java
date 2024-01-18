package br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.FoundExistingExecption;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.repository.UsuarioRepository;

@Service
public class CreateUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario execute(Usuario usuario){
         this.usuarioRepository.findByNome(usuario.getNome())
        .ifPresent(user -> {
            throw new FoundExistingExecption("usuário ja existe na base de dados");
        });

        return this.usuarioRepository.save(usuario);
    }
}
