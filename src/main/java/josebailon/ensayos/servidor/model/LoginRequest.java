/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model;

import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Getter //autogenerar getters
@Builder //autogenerar constructor
public class LoginRequest {
    private String username;
    private String password;
}//end LoginRequest
