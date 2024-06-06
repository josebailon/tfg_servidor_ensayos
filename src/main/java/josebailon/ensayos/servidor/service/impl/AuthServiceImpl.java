/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import josebailon.ensayos.servidor.model.auth.LoginResponse;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import josebailon.ensayos.servidor.security.JwtCreadorToken;
import josebailon.ensayos.servidor.security.JwtPropiedades;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.IAuthService;
import josebailon.ensayos.servidor.service.exception.DuplicatedEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementacion del servicio de autorizacion
 * @author Jose Javier Bailon Ortiz
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{
    private final JwtCreadorToken jwtCreadorToken;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repositorio;
    private Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());

    @Override
    public LoginResponse intentoLogin(String email, String password){
        
        logger.log(Level.INFO, "Login intento: {0}", email);
        //efectuar la comprobacion de autenticacion
        Authentication autenticacion = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password) );
        
        
        //establecer el resultado de la autenticacion en el contexto
        SecurityContextHolder.getContext().setAuthentication(autenticacion);
        
        UserPrincipal userPrincipal = (UserPrincipal)autenticacion.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toList();
        logger.log(Level.INFO, "Login correcto: {0}", email);
        //crear el token de autentificacion y devolverlo
        String token = jwtCreadorToken.crear(userPrincipal.getUserId(), userPrincipal.getEmail(), roles);
        return LoginResponse.builder().accessToken(token).build();
    }

    
    
    
    @Override
    public Usuario registrar(String email, String password) throws DuplicatedEmailException {
        logger.log(Level.INFO, "Intento de registro: {0}}", email);
        if (!repositorio.findByEmail(email).isEmpty()) {
            throw new DuplicatedEmailException(String.format("El email: %s ya está ocupado", email));
        }
   
        
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRole("usuario");
        return repositorio.save(usuario);
    }
}//end AuthService
