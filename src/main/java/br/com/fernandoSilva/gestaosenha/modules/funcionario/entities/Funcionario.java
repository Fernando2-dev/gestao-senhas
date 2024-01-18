package br.com.fernandoSilva.gestaosenha.modules.funcionario.entities;


import br.com.fernandoSilva.gestaosenha.modules.funcionario.dto.FuncionarioAtualizacaoDto;
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
public class Funcionario implements Pessoa<FuncionarioAtualizacaoDto>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo [Nome] não pode ser nullo")
    private String nome;


    @Override
    public FuncionarioAtualizacaoDto atualizar(FuncionarioAtualizacaoDto funcionarioAtualizacaoDto) {
        if (funcionarioAtualizacaoDto.nome() != null) {
            this.setNome(funcionarioAtualizacaoDto.nome());
        }
        return funcionarioAtualizacaoDto;
    }
}
