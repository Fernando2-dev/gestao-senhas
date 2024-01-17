package br.com.fernandoSilva.gestaosenha.modules.execption;

public class BadRequestExecption extends RuntimeException{
    public BadRequestExecption(String mensagem){
        super(mensagem);
    }
}
