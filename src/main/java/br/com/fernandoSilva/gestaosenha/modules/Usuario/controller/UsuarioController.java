package br.com.fernandoSilva.gestaosenha.modules.Usuario.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernandoSilva.gestaosenha.modules.Usuario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.Usuario.useCase.CreateUsuarioUseCase;
import br.com.fernandoSilva.gestaosenha.modules.Usuario.useCase.DeleteUsuarioUseCase;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private CreateUsuarioUseCase createUsuarioUseCase;

    @Autowired
    private DeleteUsuarioUseCase deleteUsuarioUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Usuario usuario) {
        try {
            var pessoa = this.createUsuarioUseCase.execute(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            this.deleteUsuarioUseCase.execute(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
