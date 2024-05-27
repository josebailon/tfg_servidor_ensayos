/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/**
 * Define el formato de una peticion para hacer un registro
 * @author Jose Javier Bailon Ortiz
 */
@Getter //autogenerar getters
@Builder //autogenerar constructor
public class RegistrarRequest {
    @Email(message = "Email no valido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "El email no puede estar vacio")
    private String email;
    @Size(min = 4, message = "Contraseña demasiado corta")
    @Size(max = 8, message = "Contraseña demasiado larga")
    @NotEmpty(message = "La contraseña no puede estar vacia")
    private String password;
}//end LoginRequest
