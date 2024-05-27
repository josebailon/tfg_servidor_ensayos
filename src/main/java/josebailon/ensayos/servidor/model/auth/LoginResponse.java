/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model.auth;

import lombok.Builder;
import lombok.Getter;

/**
 * Define el formato de una respuesta de login exitoso
 * @author Jose Javier Bailon Ortiz
 */
@Getter
@Builder
public class LoginResponse {
 private final String accessToken;
}//end LoginResponse
