/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Cancion;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.ICancionService;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controlador de endpoints para canciones
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/cancion")
@RequiredArgsConstructor
public class CancionController {
    
    /**
     * Servicio de canciones
     */
    private final ICancionService cancionService;
    
    /**
     * Endpoint para crear una cancion
     * @param request Request con la cancion a crear
     * @param idgrupo UUID del grupo al que asignar la cancion
     * @param principal UserPrincipal de quien hace la peticion
     * @return La cancion creada
     */
    @PostMapping("/{idgrupo}")
    @JsonView(Vista.Esencial.class)
    public Cancion create(@RequestBody @Valid Cancion request, @PathVariable String idgrupo, @AuthenticationPrincipal UserPrincipal principal){
        return cancionService.create(request,UUID.fromString(idgrupo), principal.getUserId());
    }

    /**
     * Endpoint para editar una cancion
     * @param request Request con la cancion a editar
     * @param principal UserPrincipal de quien hace la peticion
     * @return La cancion editada
     * @throws ResponseStatusException Si no se tiene permiso para editar la cancion
     * @throws VersionIncorrectaException  Si se ha intentado editar con una version incorrecta
     */
    @PutMapping()
    @JsonView(Vista.Esencial.class)
    public Cancion edit(@RequestBody @Valid Cancion request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        return cancionService.edit(request, principal.getUserId());
    }
    
    /**
     * Endpoint para borrar una cancion
     * @param idcancion UUID de la cancion
     * @param principal UserPrincipal de quien hace la peticion
     * @return Response vacia con status 200 si se ha borrado bien
     * @throws ResponseStatusException Si no se tienen permisos para borrar
     * @throws VersionIncorrectaException 
     */
    @DeleteMapping("/{idcancion}")
    @JsonView(Vista.Esencial.class)
    public ResponseEntity delete(@PathVariable String idcancion, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        cancionService.delete(UUID.fromString(idcancion), principal.getUserId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
 
    
    
}//end AuthController
