/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de acceso a propiedades de audio
 * @author Jose Javier Bailon Ortiz
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("almacenamiento")
public class AudioPropiedades {
    /**
     * Ruta en la que se almacenan los archivos de audio
     */
    private String ruta;
}//end JwtProperties
