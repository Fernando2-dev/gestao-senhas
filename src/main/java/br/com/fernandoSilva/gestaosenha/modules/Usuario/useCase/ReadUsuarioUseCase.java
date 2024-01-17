package br.com.fernandoSilva.gestaosenha.modules.Usuario.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.Usuario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.Usuario.repository.UsuarioRepository;
import br.com.fernandoSilva.gestaosenha.modules.execption.UserNotFoundExecption;

@Service
public class ReadUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> execute() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new UserNotFoundExecption("[] Não existe usuários cadastrados no banco de dados!");
        }

        return usuarios;
    }

}
