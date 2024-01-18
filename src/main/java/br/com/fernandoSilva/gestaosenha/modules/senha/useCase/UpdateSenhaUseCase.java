package br.com.fernandoSilva.gestaosenha.modules.senha.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;
import br.com.fernandoSilva.gestaosenha.modules.senha.criptografia.PasswordEncriptor;
import br.com.fernandoSilva.gestaosenha.modules.senha.dto.SenhaAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.senha.entities.Senha;
import br.com.fernandoSilva.gestaosenha.modules.senha.repository.SenhaRepository;

@Service
public class UpdateSenhaUseCase {

    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private PasswordEncriptor passwordEncriptor;

    public Senha execute(SenhaAtualizacaoDto senhaAtualizacaoDto) {
        var senha = this.senhaRepository.findById(senhaAtualizacaoDto.id())
                .orElseThrow(() -> {
                    throw new NotFoundExecption("id não encontrado, por favor, digite um id válido");
                });
        try {
            senha.atualizar(senhaAtualizacaoDto);

            var newPassword = this.passwordEncriptor.encrypt(senhaAtualizacaoDto.senha());
            senha.setSenha(newPassword);

            return this.senhaRepository.save(senha);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar senha", e);
        }

    }

}
