package br.com.fernandoSilva.gestaosenha.modules.senha.useCase;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoSilva.gestaosenha.modules.execption.FoundExistingExecption;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.repository.UsuarioRepository;
import br.com.fernandoSilva.gestaosenha.modules.senha.criptografia.PasswordEncriptor;
import br.com.fernandoSilva.gestaosenha.modules.senha.entities.Senha;
import br.com.fernandoSilva.gestaosenha.modules.senha.repository.SenhaRepository;

@Service
public class CreateSenhaUseCase {
    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private PasswordEncriptor passwordEncriptor;

    public Senha execute(Senha senha) throws Exception {
        if (senha.getIdUsuario() != null) {
            Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(senha.getIdUsuario());

            if (usuarioOptional.isEmpty()) {
                throw new FoundExistingExecption("Usuário com o ID fornecido não existe na base de dados.");
            }

            List<Senha> senhasUsuario = this.senhaRepository.findByIdUsuario(senha.getIdUsuario());

            if (senhasUsuario.size() >= 20) {
                throw new FoundExistingExecption("Usuário já possui o número máximo de senhas permitidas (20).");
            }
        }

        if (this.senhaRepository.findBySenha(this.passwordEncriptor.encrypt(senha.getSenha())).isPresent()) {
            throw new FoundExistingExecption("Senha já registrada no banco de dados, por favor gere uma senha diferente na qual já existe cadastrada!");
        }

        try {
            var password = this.passwordEncriptor.encrypt(senha.getSenha());

            senha.setSenha(password);

            return this.senhaRepository.save(senha);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar senha", e);
        }
    }
}
