package br.com.fernandoSilva.gestaosenha.modules.Usuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.Usuario.repository.UsuarioRepository;
import br.com.fernandoSilva.gestaosenha.modules.execption.UserNotFoundExecption;

@Service
public class DeleteUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void execute(Long id){
        var pessoaId = this.usuarioRepository.findById(id)
        .orElseThrow(() -> {
            throw new UserNotFoundExecption("Usuário não encontrado na base de dados !");
        });
       this.usuarioRepository.delete(pessoaId);     
    }
    
}
