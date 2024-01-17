package br.com.fernandoSilva.gestaosenha.modules.execption;

public class UserFoundExistingExecption extends RuntimeException{
    public UserFoundExistingExecption(String mensagem){
        super(mensagem);
    }
}
