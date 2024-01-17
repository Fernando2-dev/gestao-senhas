package br.com.fernandoSilva.gestaosenha.modules.execption;

public class UserBadRequestExecption extends RuntimeException{
    public UserBadRequestExecption(String mensagem){
        super(mensagem);
    }
}
