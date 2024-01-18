package br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.dto.FuncionarioAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.entities.Funcionario;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.repository.FuncionarioRepository;

@Service
public class UpdateFuncionarioUseCase {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario execute(FuncionarioAtualizacaoDto funcionarioAtualizacaoDto) {
        var usuario = this.funcionarioRepository.findById(funcionarioAtualizacaoDto.id())
                .orElseThrow(() -> {
                    throw new NotFoundExecption("id não encontrado, por favor, digite um id válido");
                });

        usuario.atualizar(funcionarioAtualizacaoDto);
        return this.funcionarioRepository.save(usuario);

    }
}
