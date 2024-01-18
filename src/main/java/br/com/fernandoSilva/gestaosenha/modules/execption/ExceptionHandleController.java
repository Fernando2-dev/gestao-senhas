package br.com.fernandoSilva.gestaosenha.modules.execption;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandleController {

    private MessageSource messageSource;

    public ExceptionHandleController(MessageSource message){
       this.messageSource = message;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentsNotValidException(MethodArgumentNotValidException e) {
        
        List<ErrorMessageDTO> dto = new ArrayList<>();
      
        e.getBindingResult().getFieldErrors().forEach(err -> {
         String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale(null));
         ErrorMessageDTO error = new ErrorMessageDTO(mensagem, err.getField());
         dto.add(error);
      });
      return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
