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
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.IGrupoService;
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
 * Controlador de endpoints para grupos
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/grupo")
@RequiredArgsConstructor
public class GrupoController {
    
    /**
     * Servicio de grupos
     */
    private final IGrupoService grupoService;
    
 
    /**
     * Endpoint para crear un grupo
     * @param request Peticion con el grupo a crear
     * @param principal UserPrincipal de quien hace la peticion
     * @return El grupo creado
     */
    @JsonView(Vista.Esencial.class)
    @PostMapping()
    public Grupo create(@RequestBody @Valid Grupo request, @AuthenticationPrincipal UserPrincipal principal){
        return grupoService.create(request, principal.getUserId());
    }
    
    /**
     * Endpoint para editar un grupo
     * @param request El grupo a editar
     * @param principal UserPrincipal de quien hace la peticion
     * @return El grupo editado
     * @throws ResponseStatusException Si no se tienen permisos para editar el grupo
     * @throws VersionIncorrectaException  Si se ha intentado editar con version incorrecta
     */
    @JsonView(Vista.Esencial.class)
    @PutMapping()
    public Grupo edit(@RequestBody @Valid Grupo request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        return grupoService.edit(request, principal.getUserId());
    }
    
    /**
     * Endpoint para eliminar un grupo
     * @param idgrupo UUID del grupo a eliminar
     * @param principal UserPrincipal de quien hace la peticion
     * @return Response vacia con status 200 si se ha eliminado correctamente
     * @throws ResponseStatusException Si no se tienen permisos para eliminar
     * @throws VersionIncorrectaException 
     */
    @JsonView(Vista.Esencial.class)
    @DeleteMapping("/{idgrupo}")
    public ResponseEntity delete(@PathVariable String idgrupo, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        grupoService.delete(UUID.fromString(idgrupo), principal.getUserId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    /**
     * Endpoint para asignar un usuario a un grupo
     * @param idgrupo UUID del grupo al que asignar el usuario
     * @param emailusuario Email del usuario a asignar al grupo
     * @param principal UserPrincipal de quien hace la peticion
     * @return El grupo tras la insercion
     */
    @JsonView(Vista.Esencial.class)
    @PostMapping("/{idgrupo}/{emailusuario}")
    public Grupo addUsuario(@PathVariable String idgrupo, @PathVariable String emailusuario, @AuthenticationPrincipal UserPrincipal principal){
                
        return grupoService.addUsuario(UUID.fromString(idgrupo), emailusuario,principal.getUserId());
    }
    
    /**
     * Endpoint para sacar un usuario de un grupo
     * @param idgrupo UUID del grupo del que sacar el usuario
     * @param emailusuario Email del usuario que sacar del grupo
     * @param principal UserPrincipal de quien hace la peticion
     * @return El grupo tras quitar el usuario
     */
    @JsonView(Vista.Esencial.class)
    @DeleteMapping("/{idgrupo}/{emailusuario}")
    public Grupo deleteUsuario(@PathVariable String idgrupo, @PathVariable String emailusuario, @AuthenticationPrincipal UserPrincipal principal){
        return grupoService.deleteUsuario(UUID.fromString(idgrupo), emailusuario,principal.getUserId());
    }

    
    
}//end AuthController
