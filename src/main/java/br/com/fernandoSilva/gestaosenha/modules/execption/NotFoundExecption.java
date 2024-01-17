package br.com.fernandoSilva.gestaosenha.modules.execption;

public class NotFoundExecption extends RuntimeException{
    public NotFoundExecption(String mensagem){
        super(mensagem);
    }
}
