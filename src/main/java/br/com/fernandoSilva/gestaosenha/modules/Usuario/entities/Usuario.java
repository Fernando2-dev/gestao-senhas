package br.com.fernandoSilva.gestaosenha.modules.Usuario.entities;


import br.com.fernandoSilva.gestaosenha.modules.Usuario.dto.UsuarioAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.interfaces.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario implements Pessoa<UsuarioAtualizacaoDto>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;

    @Override
    public UsuarioAtualizacaoDto atualizar(UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        if (usuarioAtualizacaoDto.nome() != null) {
            this.setNome(usuarioAtualizacaoDto.nome());
        }
        return usuarioAtualizacaoDto;
    }
}
