package br.com.campanha.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST )
public class BusinessException extends Exception {

    private String messageKey;

    public BusinessException( String message, String messageKey ) {
        super(message);
        this.messageKey = messageKey;
    }
}
