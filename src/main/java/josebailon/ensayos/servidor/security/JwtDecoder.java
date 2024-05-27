/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Decodifica un token jwt
 * @author Jose Javier Bailon Ortiz
 */
@Component
@RequiredArgsConstructor
public class JwtDecoder {
    private final JwtPropiedades propiedades;
    
    /**
     * Decodifica un token
     * @param token Token a decodificar
     * @return Un token JWT decodificado
     */
    public DecodedJWT decode(String token){
        try{
            return JWT.require(Algorithm.HMAC256(propiedades.getSecretKey())).build().verify(token);
        }catch(Exception ex)
        {
            //null si no se ha podido procesar el token y decodificarlo
            return null;
        }
    }
}//end JwtDecoder
