/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Conversor de token jwt a UserPrincipal
 * @author Jose Javier Bailon Ortiz
 */
@Component
public class JwtToUserPrincipalConverter {
    public UserPrincipal convertir(DecodedJWT jwt){
        return UserPrincipal.builder()
                .UserId(Long.valueOf(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .authorities(extraerAuthoritiesDeClaim(jwt))
                .build();
                
    }
    
    /** 
     * Extrae las autorities del claim autorities del token
     * @param jwt El token JWT decodificado
     * @return  Lista de GrantedAutority
     */
    private List<SimpleGrantedAuthority> extraerAuthoritiesDeClaim(DecodedJWT jwt){
        Claim claim = jwt.getClaim("authorities");
        if (claim.isNull() || claim.isMissing())
            return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
    
}//end JwtToPrincipalConverter
