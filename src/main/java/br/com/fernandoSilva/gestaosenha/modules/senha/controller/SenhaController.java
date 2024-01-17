package br.com.fernandoSilva.gestaosenha.modules.senha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernandoSilva.gestaosenha.modules.senha.entities.Senha;
import br.com.fernandoSilva.gestaosenha.modules.senha.useCase.CreateSenhaUseCase;
import br.com.fernandoSilva.gestaosenha.modules.senha.useCase.DeleteSenhaUseCase;
import br.com.fernandoSilva.gestaosenha.modules.senha.useCase.ReadSenhaUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/senha")
public class SenhaController {

    @Autowired
    private CreateSenhaUseCase createSenhaUseCase;

    @Autowired
    private ReadSenhaUseCase readSenhaUseCase;

    @Autowired
    private DeleteSenhaUseCase deleteSenhaUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Senha senha) {
        try {
            var senhas = this.createSenhaUseCase.execute(senha);
            return ResponseEntity.status(HttpStatus.CREATED).body(senhas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> read() {
        try {
            List<Senha> senhas = this.readSenhaUseCase.execute();
            return ResponseEntity.ok(senhas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            this.deleteSenhaUseCase.execute(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
