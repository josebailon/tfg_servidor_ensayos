/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Ejecuta el filtro de autenticacion para las peticiones
 * @author Jose Javier Bailon Ortiz
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    /**
     * Decodificador JWT
     */
    private final JwtDecoder jwtDecoder;
    
    /**
     * Transofrmador de JWT a user principal
     */
    private final JwtToUserPrincipalConverter jwtToUserDetallesconverter;
    
    /**
     * Servicio de user principal
     */
    private final UserPrincipalService userPrincipalService;
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        extraerTokenDePeticion(request)
                .map(str -> jwtDecoder.decode(str))// decodificar el token
                .map(token->jwtToUserDetallesconverter.convertir(token)) // extraer userdetalles
                .map(userDetalles -> new UserPrincipalAuthenticationToken(userDetalles,userPrincipalService)) //crear userdetalles con su autenticacion
                .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));// incluir autenticador en el contexto
        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token de la peticion. Como se extrae del header con Bearer hay que restar el inicio de la cadena
     * @param request Peticion que contiene el token
     * @return  Un optional con el token
     */
    private Optional<String> extraerTokenDePeticion(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token)){
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }
}//end JwtAuthenticationFilter
