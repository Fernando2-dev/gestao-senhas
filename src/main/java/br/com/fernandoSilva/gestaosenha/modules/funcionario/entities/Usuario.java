package br.com.fernandoSilva.gestaosenha.modules.funcionario.entities;


import br.com.fernandoSilva.gestaosenha.modules.funcionario.dto.UsuarioAtualizacaoDto;
import br.com.fernandoSilva.gestaosenha.modules.interfaces.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "usuario")
@Table(name = "tb_usuario")
public class Usuario implements Pessoa<UsuarioAtualizacaoDto>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo [Nome] não pode ser nullo")
    private String nome;


    @Override
    public UsuarioAtualizacaoDto atualizar(UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        if (usuarioAtualizacaoDto.nome() != null) {
            this.setNome(usuarioAtualizacaoDto.nome());
        }
        return usuarioAtualizacaoDto;
    }
}
