package josebailon.ensayos.servidor.service.exception;

import lombok.Getter;

@Getter
public class VersionIncorrectaException extends Exception {
    Object valor;
    public VersionIncorrectaException(String message,Object valor) {
        super(message);
        this.valor=valor;
    }
    
}
