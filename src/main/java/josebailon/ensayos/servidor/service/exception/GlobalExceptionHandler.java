/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<String> handleDuplicatedEmailException(final DuplicatedEmailException ex){
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(final ResponseStatusException ex){
        return new ResponseEntity<String>(ex.getMessage(),ex.getStatusCode());
    }

    @ExceptionHandler(VersionIncorrectaException.class)
     @RequestMapping(produces = "application/json")
    public ResponseEntity<Object> handleVersionIncorrectaException(final VersionIncorrectaException ex){
        return new ResponseEntity<Object>(ex.getValor(),HttpStatus.CONFLICT);

    }

    
}//end GlobalExceptionHandler
