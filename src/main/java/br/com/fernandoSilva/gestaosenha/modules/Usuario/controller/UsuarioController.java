package br.com.fernandoSilva.gestaosenha.modules.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.fernandoSilva.gestaosenha.modules.usuario.dto.UsuarioAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.usuario.entities.Usuario;
import br.com.fernandoSilva.gestaosenha.modules.usuario.useCase.CreateUsuarioUseCase;
import br.com.fernandoSilva.gestaosenha.modules.usuario.useCase.DeleteUsuarioUseCase;
import br.com.fernandoSilva.gestaosenha.modules.usuario.useCase.ReadUsuarioUseCase;
import br.com.fernandoSilva.gestaosenha.modules.usuario.useCase.UpdateUsuarioUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private CreateUsuarioUseCase createUsuarioUseCase;

    @Autowired
    private DeleteUsuarioUseCase deleteUsuarioUseCase;

    @Autowired
    private ReadUsuarioUseCase readUsuarioUseCase;

    @Autowired
    private UpdateUsuarioUseCase updateUsuarioUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Usuario usuario) {
        try {
            var pessoa = this.createUsuarioUseCase.execute(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> atualizacao(@Valid @RequestBody UsuarioAtualizacaoDto usuario) {
        try {
            var pessoa = this.updateUsuarioUseCase.execute(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(pessoa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> read() {
        try {
            List<Usuario> usuarios = this.readUsuarioUseCase.execute();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
