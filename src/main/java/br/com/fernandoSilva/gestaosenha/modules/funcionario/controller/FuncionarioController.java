package br.com.fernandoSilva.gestaosenha.modules.funcionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernandoSilva.gestaosenha.modules.funcionario.dto.FuncionarioAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.entities.Funcionario;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase.CreateFuncionarioUseCase;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase.DeleteFuncionarioUseCase;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase.ReadFuncionarioUseCase;
import br.com.fernandoSilva.gestaosenha.modules.funcionario.useCase.UpdateFuncionarioUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private CreateFuncionarioUseCase createFuncionarioUseCase;

    @Autowired
    private DeleteFuncionarioUseCase deleteFuncionarioUseCase;

    @Autowired
    private ReadFuncionarioUseCase readFuncionarioUseCase;

    @Autowired
    private UpdateFuncionarioUseCase updateFuncionarioUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Funcionario funcionario) {
        try {
            var pessoa = this.createFuncionarioUseCase.execute(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> atualizacao(@Valid @RequestBody FuncionarioAtualizacaoDto funcionario) {
        try {
            var pessoa = this.updateFuncionarioUseCase.execute(funcionario);
            return ResponseEntity.status(HttpStatus.OK).body(pessoa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> read() {
        try {
            List<Funcionario> usuarios = this.readFuncionarioUseCase.execute();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            this.deleteFuncionarioUseCase.execute(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
