package br.com.fernandoSilva.gestaosenha.modules.execption;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private String mensagem;
    private String campo;
}
