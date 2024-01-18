package br.com.fernandoSilva.gestaosenha.modules.senha.entities;

import br.com.fernandoSilva.gestaosenha.modules.interfaces.Pessoa;
import br.com.fernandoSilva.gestaosenha.modules.senha.dto.SenhaAtualizacaoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "senha")
@Table(name = "tb_senha")
@AllArgsConstructor
@NoArgsConstructor
public class Senha implements Pessoa<SenhaAtualizacaoDto>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo [Descrição] não pode ser nullo")
    private String descricao;

    @NotBlank(message = "O campo [Tag] não pode ser nullo")
    private String tag;

    @NotBlank(message = "O campo [Senha] não pode ser nullo")
    private String senha;

    @NotNull(message = "O campo [Id Usuário] não pode ser nullo")
    @Column(name = "id_usuario")
    private Long id_usuario;

    @Override
   public SenhaAtualizacaoDto atualizar(SenhaAtualizacaoDto senhaAtualizacaoDto) {
        if (senhaAtualizacaoDto.descricao() != null) {
            this.setDescricao(senhaAtualizacaoDto.descricao());
        }
        if (senhaAtualizacaoDto.tag() != null) {
            this.setTag(senhaAtualizacaoDto.tag());
        }
        if (senhaAtualizacaoDto.senha() != null) {
            this.setSenha(senhaAtualizacaoDto.senha());
        }
        return senhaAtualizacaoDto;
    }
    

}
