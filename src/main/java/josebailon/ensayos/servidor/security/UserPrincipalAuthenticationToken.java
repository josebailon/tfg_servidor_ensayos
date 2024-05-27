/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Clase de autentificacion en base a un usuario que se ha sacado de un token
 * 
 * @author Jose Javier Bailon Ortiz
 */

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrincipal principal;

    public UserPrincipalAuthenticationToken(UserPrincipal principal, UserPrincipalService userPrincipalService) {
        super(principal.getAuthorities());
        this.principal=principal;
        boolean autenticado =false;
        //si no puede cargar el usuario entonces salta la excepcion y no se autentica
        try{
            userPrincipalService.loadUserByUsername(principal.getEmail());
            autenticado=true;

        }catch(Exception ex){
            System.out.println("Usuario no existente, cancelando autorizacion");
        }
        //definicion de la autenticacion
        this.setAuthenticated(autenticado);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }

}//end UserPrincipalAuthenticationToken
