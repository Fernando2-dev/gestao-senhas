package br.com.fernandoSilva.gestaosenha.modules.senha.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;
import br.com.fernandoSilva.gestaosenha.modules.senha.repository.SenhaRepository;

@Service
public class DeleteSenhaUseCase {
     
    @Autowired
     private SenhaRepository senhaRepository; 

    public void execute(Long id){
        var pessoaId = this.senhaRepository.findById(id)
        .orElseThrow(() -> {
            throw new NotFoundExecption("Senha não encontrado na base de dados !");
        });
       this.senhaRepository.delete(pessoaId);     
    }
}
