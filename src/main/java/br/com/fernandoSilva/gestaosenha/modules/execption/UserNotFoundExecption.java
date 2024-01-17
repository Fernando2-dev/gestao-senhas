package br.com.fernandoSilva.gestaosenha.modules.execption;

public class UserNotFoundExecption extends RuntimeException{
    public UserNotFoundExecption(String mensagem){
        super(mensagem);
    }
}
