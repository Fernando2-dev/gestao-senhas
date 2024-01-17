package br.com.fernandoSilva.gestaosenha.modules.senha.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "senha")
@Table(name = "tb_senha")
@AllArgsConstructor
@NoArgsConstructor
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String tag;
    private String senha;

    @Column(name = "id_usuario")
    private Long id_usuario;
    

}
