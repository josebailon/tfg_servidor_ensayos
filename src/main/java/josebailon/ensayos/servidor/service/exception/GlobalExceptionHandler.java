/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service.exception;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import josebailon.ensayos.servidor.model.vistas.Vista;
import org.hibernate.NonUniqueObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;

/**
 * Gestor global de excepciones de las peticiones
 * @author Jose Javier Bailon Ortiz
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handler de email duplicado
     * @param ex
     * @return Respuesta con mensaje y estatus 409 conflicto
     */
    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<String> handleDuplicatedEmailException(final DuplicatedEmailException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    
    /**
     * Handler general para cualquier excepcion de status
     * @param ex
     * @return  Response con el status definido en la excepcion
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(final ResponseStatusException ex) {
        return new ResponseEntity<String>(ex.getMessage(), ex.getStatusCode());
    }

    
    /**
     * Handler de version incorrecta
     * @param ex
     * @return Respuesta con el valor del objeto con el que se ha generado conflicto y el stattus 409
     */
    @ExceptionHandler(VersionIncorrectaException.class)
    @RequestMapping(produces = "application/json")
    @JsonView(Vista.Esencial.class)
    public ResponseEntity<Object> handleVersionIncorrectaException(final VersionIncorrectaException ex) {
        return new ResponseEntity<Object>(ex.getValor(), HttpStatus.CONFLICT);

    }

    
    /**
     * Handler de objeto duplicado
     * @param ex
     * @return  Respuesta con mensaje y status 409
     */
    @ExceptionHandler(NonUniqueObjectException.class)
    @RequestMapping(produces = "application/json")
    public ResponseEntity<Object> handleNonUniqueObjectException(final NonUniqueObjectException ex) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.CONFLICT);

    }

    
    /**
     * Handler de peticiones no legibles
     * @param ex
     * @return REspuesta con mensaje y status 400 bad request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @RequestMapping(produces = "application/json")
    @JsonView(Vista.Esencial.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    
    /**
     * Handler de peticiones multipart donde una parte no existe
     * @param ex
     * @return REspuesta con mensaje y estatus 400 bad request
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @RequestMapping(produces = "application/json")
    public ResponseEntity<Object> handleMissingServletRequestPartException(final MissingServletRequestPartException ex) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    
    /**
     * Handler para exepciones de argumentos no validos (por ejemplo valore no permitidos)
     * @param ex
     * @return REspuesta con los errores y el status 400 bad request 
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @RequestMapping(produces = "application/json")
    public ResponseEntity<Object> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    
    /**
     * Handler para excepciones de violacion de constraints.
     * Sucede al intentar hacer peticiones con valores no permitidos segun las especificaciones de las entidades
     * @param ex
     * @return REspuesta con el objeto que ha dado lugar a la excepcion y un status 400 bad request
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @RequestMapping(produces = "application/json")
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {
        return new ResponseEntity<Object>(ex.getConstraintViolations(), HttpStatus.BAD_REQUEST);
    }
    
}//end GlobalExceptionHandler
