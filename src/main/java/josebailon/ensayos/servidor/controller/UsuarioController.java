/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import java.util.Optional;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controlador de endpoints para acciones generales sobre usuarios
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    
    /**
     * SErvicio de suaurios
     */
    private final IUsuarioService usuarioService;
    
    /**
     * Endpoint para recoger todos los datos a los que tiene acceso un usuario
     * @param principal UserPrincipal de quien hace la peticion
     * @return Arbol JSON con los grupos, canciones, notas y audios a los que el usuario definido en principal tiene acceso
     */
    @JsonView(Vista.Completa.class)
    @GetMapping("/grupos")
    public List<Grupo> gruposDeUsuario(@AuthenticationPrincipal UserPrincipal principal){
        return usuarioService.getGruposCompletos(principal.getUserId());
    }

    /**
     * Endpoint para comprobar si un email esta usado
     * @param email Email a comprobar
     * @param principal UserPrincipal de quien hace la peticion
     * @return El usuario si existe
     * @throws ResponseStatusException  Response con status 404 not found si no existe el usuario
     */
    @JsonView(Vista.Esencial.class)
    @GetMapping("/existe/{email}")
    public Usuario usuario(@PathVariable String email, @AuthenticationPrincipal UserPrincipal principal) throws ResponseStatusException{
        Optional<Usuario> usuario = usuarioService.findByEmail(email);
        if (!usuario.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
        return usuarioService.findByEmail(email).get();
    }    
}//end AuthController
