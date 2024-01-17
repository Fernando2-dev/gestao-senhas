package br.com.fernandoSilva.gestaosenha.modules.execption;

public class FoundExistingExecption extends RuntimeException{
    public FoundExistingExecption(String mensagem){
        super(mensagem);
    }
}
