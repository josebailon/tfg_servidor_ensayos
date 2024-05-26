/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.UUID;
import josebailon.ensayos.servidor.config.AudioPropiedades;
import josebailon.ensayos.servidor.model.entity.Audio;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.IAudioService;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/audio")
@RequiredArgsConstructor
public class AudioController {
    private final AudioPropiedades audioPropiedades;
    private final IAudioService audioService;
    
    
    @PostMapping("")
    @JsonView(Vista.Esencial.class)
    public Audio create( @RequestPart("datos") @Valid Audio request, @RequestPart("archivo") MultipartFile archivo, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        return audioService.create(request,archivo, principal.getUserId());
    }
    
    
    @PutMapping("")
    @JsonView(Vista.Esencial.class)
    public Audio edit(@RequestPart("datos") @Valid Audio request, @RequestPart("archivo") MultipartFile archivo, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        System.out.println(request);
        return audioService.edit(request, archivo, principal.getUserId());
    }
    
   @DeleteMapping("/{idaudio}")
    @JsonView(Vista.Esencial.class)
    public ResponseEntity delete(@PathVariable String idaudio, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        audioService.delete(UUID.fromString(idaudio), principal.getUserId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    
 
 
    @GetMapping(path = "/{audioid}")
public ResponseEntity<Resource> get(@PathVariable String audioid,@AuthenticationPrincipal UserPrincipal principal) throws IOException {
    
        return audioService.get(UUID.fromString(audioid),principal.getUserId());
}
    
}//end AuthController
