/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Cancion;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.ICancionService;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequiredArgsConstructor
public class CancionController {
    
    private final ICancionService cancionService;
    //Anotaciones
    //RequestBody transforma el cuerpo de la peticion http al objeto java
    //Validated valida la request segun las anotaciones del tipo de objeto (en este caso LoginRequest)
    @PostMapping("/cancion/{idgrupo}")
    @JsonView(Vista.Esencial.class)
    public Cancion create(@RequestBody Cancion request, @PathVariable String idgrupo, @AuthenticationPrincipal UserPrincipal principal){
        return cancionService.create(request.getId(), request.getNombre(), request.getDescripcion(),request.getDuracion(), request.getVersion(),UUID.fromString(idgrupo), principal.getUserId());
    }

    @PutMapping("/cancion")
    @JsonView(Vista.Esencial.class)
    public Cancion edit(@RequestBody @Validated Cancion request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        return cancionService.edit(request, principal.getUserId());
    }
    @DeleteMapping("/cancion")
    @JsonView(Vista.Esencial.class)
    public Cancion delete(@RequestBody @Validated Cancion request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        return cancionService.delete(request, principal.getUserId());
    }
 
    
    
}//end AuthController
