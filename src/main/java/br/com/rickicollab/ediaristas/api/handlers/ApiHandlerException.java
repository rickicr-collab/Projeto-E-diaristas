package br.com.rickicollab.ediaristas.api.handlers;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.rickicollab.ediaristas.api.dtos.response.ErrorResponse;
import br.com.rickicollab.ediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
public class ApiHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EnderecoServiceException.class)
    public ResponseEntity<Object> handlerEnderecoServiceException(EnderecoServiceException exception, HttpServletRequest request) {
            var errorResponse = ErrorResponse.builder()
                    .status(400)
                    .timestamp(LocalDateTime.now())
                    .mensagem(exception.getLocalizedMessage())
                    .path(request.getRequestURI())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
    }   

}
