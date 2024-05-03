/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model.audio;

import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Getter //autogenerar getters
@Builder //autogenerar constructor
public class AudioRequest {
    @NotEmpty
    private UUID notaId;
    @NotEmpty
    private int notaVersion;
}//end LoginRequest
