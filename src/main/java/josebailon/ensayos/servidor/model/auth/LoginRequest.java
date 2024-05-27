/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

/**
 * Define el formato de una peticion para hacer login
 * @author Jose Javier Bailon Ortizs
 */
@Getter //autogenerar getters
@Builder //autogenerar constructor
public class LoginRequest {
    @Email(message = "Email no valido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "El email no puede estar vacio")
    private String email;
    @NotEmpty(message = "La contraseña no puede estar vacia")
    private String password;
}//end LoginRequest
