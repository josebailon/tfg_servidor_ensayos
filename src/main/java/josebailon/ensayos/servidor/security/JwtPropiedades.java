/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Accesor a las propiedades de JWT (ver application.yml)
 * @author Jose Javier Bailon Ortiz
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtPropiedades {
    /**
     * Clave de seguridad para token de acceso
     */
    private String secretKey;
}//end JwtProperties
