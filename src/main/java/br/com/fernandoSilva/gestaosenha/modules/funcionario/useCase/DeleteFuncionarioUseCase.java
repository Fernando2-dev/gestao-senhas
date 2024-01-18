package br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.DataIntegrityViolationExceptionMensagem;
import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.repository.FuncionarioRepository;

@Service
public class DeleteFuncionarioUseCase {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void execute(Long id) {
        try {
            var pessoaId = this.funcionarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundExecption("Funcionário não encontrado na base de dados!"));

            this.funcionarioRepository.delete(pessoaId);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionMensagem(
                    "Não é possível excluir um funcionário que tem relação com outros registros no sistema.");
        }
    }

}
