package br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.repository.UsuarioRepository;

@Service
public class DeleteUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void execute(Long id){
        var pessoaId = this.usuarioRepository.findById(id)
        .orElseThrow(() -> {
            throw new NotFoundExecption("Usuário não encontrado na base de dados !");
        });
       this.usuarioRepository.delete(pessoaId);     
    }
    
}
