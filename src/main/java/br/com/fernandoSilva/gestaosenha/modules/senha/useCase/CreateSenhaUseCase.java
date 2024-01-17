package br.com.fernandoSilva.gestaosenha.modules.senha.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.FoundExistingExecption;
import br.com.fernandoSilva.gestaosenha.modules.senha.criptografia.PasswordEncriptor;
import br.com.fernandoSilva.gestaosenha.modules.senha.entities.Senha;
import br.com.fernandoSilva.gestaosenha.modules.senha.repository.SenhaRepository;

@Service
public class CreateSenhaUseCase {
    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private PasswordEncriptor passwordEncriptor;

    public Senha execute(Senha senha) {
        this.senhaRepository.findBySenha(senha.getSenha())
                .ifPresent(s -> {
                    throw new FoundExistingExecption("Senha já registrada no banco de dados, por favor gere uma senha diferente na qual já existe cadastrada!");});

        try {
            var password = this.passwordEncriptor.encrypt(senha.getSenha());

            senha.setSenha(password);

            return this.senhaRepository.save(senha);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar senha", e);
        }

    }
}
