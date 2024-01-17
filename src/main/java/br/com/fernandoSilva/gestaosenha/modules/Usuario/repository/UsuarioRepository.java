package br.com.fernandoSilva.gestaosenha.modules.Usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fernandoSilva.gestaosenha.modules.Usuario.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario ,Long> {
    Optional<Usuario> findByNome(String nome);
}
