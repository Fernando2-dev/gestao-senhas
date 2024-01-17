package br.com.fernandoSilva.gestaosenha.modules.senha.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.NotFoundExecption;
import br.com.fernandoSilva.gestaosenha.modules.senha.criptografia.PasswordEncriptor;
import br.com.fernandoSilva.gestaosenha.modules.senha.entities.Senha;
import br.com.fernandoSilva.gestaosenha.modules.senha.repository.SenhaRepository;

@Service
public class ReadSenhaUseCase {
    
    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private PasswordEncriptor passwordEncriptor;

    public List<Senha> execute() {
        List<Senha> senhas = this.senhaRepository.findAll();

        if (senhas.isEmpty()) {
            throw new NotFoundExecption("[] Não existe senhas cadastrados no banco de dados!");
        }
        senhas.forEach(s -> {
            try {
                String decryptedSenha = passwordEncriptor.decrypt(s.getSenha());
                s.setSenha(decryptedSenha);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao descriptografar senha", e);
            }
        });
        return senhas;
    }
}
