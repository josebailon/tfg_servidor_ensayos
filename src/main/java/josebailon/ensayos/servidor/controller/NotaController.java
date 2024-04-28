/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
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
public class NotaController {
    
    private final INotaService notaService;
    //Anotaciones
    //RequestBody transforma el cuerpo de la peticion http al objeto java
    //Validated valida la request segun las anotaciones del tipo de objeto (en este caso LoginRequest)
    @PostMapping("/nota/{idcancion}")
    @JsonView(Vista.Esencial.class)
    public Nota create(@RequestBody Nota request, @PathVariable String idcancion, @AuthenticationPrincipal UserPrincipal principal){
        return notaService.create(request.getId(), request.getNombre(), request.getTexto(), request.getVersion(),UUID.fromString(idcancion), principal.getUserId());
    }

    @PutMapping("/nota")
    @JsonView(Vista.Esencial.class)
    public Nota edit(@RequestBody @Validated Nota request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        return notaService.edit(request, principal.getUserId());
    }
    
    
    @DeleteMapping("/nota")
    @JsonView(Vista.Esencial.class)
    public ResponseEntity delete(@RequestBody @Validated Nota request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        notaService.delete(request, principal.getUserId());
        return ResponseEntity.status(HttpStatus.OK).build();
        
    }
 
    
    
}//end AuthController
