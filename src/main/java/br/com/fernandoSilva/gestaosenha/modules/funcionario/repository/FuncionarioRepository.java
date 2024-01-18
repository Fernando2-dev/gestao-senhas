package br.com.fernandoSilva.gestaosenha.modules.funcionario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fernandoSilva.gestaosenha.modules.funcionario.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario ,Long> {
    Optional<Funcionario> findByNome(String nome);
}
