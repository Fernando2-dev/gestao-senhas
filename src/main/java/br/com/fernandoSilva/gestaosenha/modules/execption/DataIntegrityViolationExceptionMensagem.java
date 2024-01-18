package br.com.fernandoSilva.gestaosenha.modules.execption;

public class DataIntegrityViolationExceptionMensagem extends RuntimeException{
    public DataIntegrityViolationExceptionMensagem(String mensagem){
       super(mensagem);
    }
}
