/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final IUsuarioService usuarioService;
    
    //Anotaciones
    //RequestBody transforma el cuerpo de la peticion http al objeto java
    //Validated valida la request segun las anotaciones del tipo de objeto (en este caso LoginRequest)
    @JsonView(Vista.Completa.class)
    @GetMapping("/grupos")
    public List<Grupo> gruposDeUsuario(@AuthenticationPrincipal UserPrincipal principal){
        return usuarioService.getGruposCompletos(principal.getUserId());
    }
    
}//end AuthController
