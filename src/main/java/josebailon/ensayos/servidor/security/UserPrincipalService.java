/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import java.util.List;
import josebailon.ensayos.servidor.model.UsuarioEntity;
import josebailon.ensayos.servidor.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Component
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService{

    private final IUsuarioService  usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioService.findByEmail(username).orElseThrow();
        return UserPrincipal.builder()
                .UserId(usuario.getId())
                .email(usuario.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(usuario.getRole())))
                .password(usuario.getPassword())
                .build();
    }

}//end CustomUserDetailService
