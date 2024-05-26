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
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/grupo")
@RequiredArgsConstructor
public class GrupoController {
    
    private final IGrupoService grupoService;
    
    //Anotaciones
    //RequestBody transforma el cuerpo de la peticion http al objeto java
    //Validated valida la request segun las anotaciones del tipo de objeto (en este caso LoginRequest)
//    @JsonView(Vista.Esencial.class)
//    @PostMapping()
//    public Grupo create(@RequestBody @Valid Grupo request, @AuthenticationPrincipal UserPrincipal principal){
//        return grupoService.create(request.getId(), request.getNombre(), request.getDescripcion(), request.getVersion(), principal.getUserId());
//    }
    @JsonView(Vista.Esencial.class)
    @PostMapping()
    public Grupo create(@RequestBody @Valid Grupo request, @AuthenticationPrincipal UserPrincipal principal){
        return grupoService.create(request, principal.getUserId());
    }
    @JsonView(Vista.Esencial.class)
    @PutMapping()
    public Grupo edit(@RequestBody @Valid Grupo request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        return grupoService.edit(request, principal.getUserId());
    }
    @JsonView(Vista.Esencial.class)
    @DeleteMapping("/{idgrupo}")
    public ResponseEntity delete(@PathVariable String idgrupo, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        grupoService.delete(UUID.fromString(idgrupo), principal.getUserId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    //Agregar usuario a gruo
    @JsonView(Vista.Esencial.class)
    @PostMapping("/{idgrupo}/{emailusuario}")
    public Grupo addUsuario(@PathVariable String idgrupo, @PathVariable String emailusuario, @AuthenticationPrincipal UserPrincipal principal){
        System.out.println(idgrupo+" "+emailusuario);
                
        return grupoService.addUsuario(UUID.fromString(idgrupo), emailusuario,principal.getUserId());
    }
    
    //Sacar usuario de grupo
    @JsonView(Vista.Esencial.class)
    @DeleteMapping("/{idgrupo}/{emailusuario}")
    public Grupo deleteUsuario(@PathVariable String idgrupo, @PathVariable String emailusuario, @AuthenticationPrincipal UserPrincipal principal){
        return grupoService.deleteUsuario(UUID.fromString(idgrupo), emailusuario,principal.getUserId());
    }

    
    
}//end AuthController
