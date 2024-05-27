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
import josebailon.ensayos.servidor.model.entity.Nota;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.INotaService;
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
 * Controlador de endpoints para notas
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/nota")
@RequiredArgsConstructor
public class NotaController {
    
    /**
     * Servicio de notas
     */
    private final INotaService notaService;
    
   /**
    * Enspoint para crear una nota
    * @param request Nota a crear
    * @param idcancion UUID de la cancion a la que asignar la nota
     * @param principal UserPrincipal de quien hace la peticion
    * @return La nota creada
    */
    @PostMapping("/{idcancion}")
    @JsonView(Vista.Esencial.class)
    public Nota create(@RequestBody @Valid Nota request, @PathVariable String idcancion, @AuthenticationPrincipal UserPrincipal principal){
        return notaService.create(request,UUID.fromString(idcancion), principal.getUserId());
    }

    /**
     * Endpoint para editar una nota
     * @param request Nota a editar
     * @param principal UserPrincipal de quien hace la peticion
     * @return La nota editada
     * @throws ResponseStatusException Si no se tienen permisos para editar la nota
     * @throws VersionIncorrectaException  Si se intenta editar la nota con una version incorrecta
     */
    @PutMapping()
    @JsonView(Vista.Esencial.class)
    public Nota edit(@RequestBody @Valid Nota request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        return notaService.edit(request, principal.getUserId());
    }
    
    
    /**
     * Endpoint de borrado de una nota
     * @param idnota UUID de la nota e eliminar
     * @param principal UserPrincipal de quien hace la peticion
     * @return Respuesta vacia con status 200 si se ha borrado
     * @throws ResponseStatusException Si no se tienen permisos para borrar
     * @throws VersionIncorrectaException 
     */
    @DeleteMapping("/{idnota}")
    @JsonView(Vista.Esencial.class)
    public ResponseEntity delete(@PathVariable String idnota, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        notaService.delete(UUID.fromString(idnota), principal.getUserId());
        return ResponseEntity.status(HttpStatus.OK).build();
        
    }
 
    
    
}//end AuthController
