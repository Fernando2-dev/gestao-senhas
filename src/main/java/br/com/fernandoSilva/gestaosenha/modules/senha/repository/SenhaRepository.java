package br.com.fernandoSilva.gestaosenha.modules.senha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fernandoSilva.gestaosenha.modules.senha.entities.Senha;

public interface SenhaRepository extends JpaRepository<Senha, Long> {
    Optional<Senha> findBySenha(String senha);
    List<Senha> findByIdUsuario(Long idUsuario);
}
