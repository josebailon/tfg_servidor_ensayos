package josebailon.ensayos.servidor.service.exception;

import lombok.Getter;

/**
 * Excepcion de versiones incorrectas
 * 
 * @author Jose Javier Bailon Ortiz
 */
@Getter
public class VersionIncorrectaException extends Exception {
    Object valor;
    public VersionIncorrectaException(String message,Object valor) {
        super(message);
        this.valor=valor;
    }
    
}
