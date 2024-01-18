package br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.FoundExistingExecption;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.entities.Funcionario;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.repository.FuncionarioRepository;

@Service
public class CreateFuncionarioUseCase {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public Funcionario execute(Funcionario funcionario){
         this.funcionarioRepository.findByNome(funcionario.getNome())
        .ifPresent(user -> {
            throw new FoundExistingExecption("Funcionário ja existe na base de dados");
        });

        return this.funcionarioRepository.save(funcionario);
    }
}
