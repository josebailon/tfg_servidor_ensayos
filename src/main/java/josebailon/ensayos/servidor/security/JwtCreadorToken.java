/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Crador de token a partir de usuario y email
 *
 * @author Jose Javier Bailon Ortiz
 */
@Component
@RequiredArgsConstructor
public class JwtCreadorToken {

    /**
     * Propiedades JWT (ver archivo application.yml)
     */
    private final JwtPropiedades properties;

    
    /**
     * Crear un token dada la id el email y la lista de roles del usuario
     * @param userId ID del usuario
     * @param email Email del usuario
     * @param roles Roles
     * @return  Token creado
     */
    public String crear(long userId, String email, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(365, ChronoUnit.DAYS)))
                .withClaim("email", email)
                .withClaim("authorities", roles)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}//end JwtIssuer
