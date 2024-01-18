package br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.entities.Funcionario;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.repository.FuncionarioRepository;

@Service
public class ReadFuncionarioUseCase {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> execute() {
        List<Funcionario> usuarios = this.funcionarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new NotFoundExecption("[] Não existe funcionários cadastrados no banco de dados!");
        }

        return usuarios;
    }

}
