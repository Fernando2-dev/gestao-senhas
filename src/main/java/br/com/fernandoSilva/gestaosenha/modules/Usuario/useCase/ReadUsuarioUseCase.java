package br.com.fernandoSilva.gestaosenha.modules.usuario.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.usuario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.usuario.repository.UsuarioRepository;
import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;

@Service
public class ReadUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> execute() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new NotFoundExecption("[] Não existe usuários cadastrados no banco de dados!");
        }

        return usuarios;
    }

}
