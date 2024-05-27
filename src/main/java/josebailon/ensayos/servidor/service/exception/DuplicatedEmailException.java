package josebailon.ensayos.servidor.service.exception;


/**
 * Excepcion lanzada cuando se encuentra un mail duplicado
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class DuplicatedEmailException extends Exception {

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
