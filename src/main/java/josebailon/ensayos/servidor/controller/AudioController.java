/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import josebailon.ensayos.servidor.config.AudioPropiedades;
import josebailon.ensayos.servidor.model.audio.AudioRequest;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.INotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequiredArgsConstructor
public class AudioController {
    
    private final INotaService notaService;
    private final AudioPropiedades audioPropiedades;
    //Anotaciones
    //RequestBody transforma el cuerpo de la peticion http al objeto java
    //Validated valida la request segun las anotaciones del tipo de objeto (en este caso LoginRequest)
    @PostMapping("/audio")
    @JsonView(Vista.Esencial.class)
    public ResponseEntity<String> subir(@RequestPart("data") AudioRequest request, @RequestPart("file") MultipartFile file, @AuthenticationPrincipal UserPrincipal principal){
        Long idUsuario=principal.getUserId();
        try {
            file.transferTo(new File(audioPropiedades.getRuta()+"/"+file.getOriginalFilename()));
            //304 not modified b
        } catch (IOException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ResponseEntity.ok().body(request.getNotaId() + " "+request.getNotaVersion());
    }
 
 
    @GetMapping(path = "/audio")
public ResponseEntity<Resource> download(String param) throws IOException {

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=install.log");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        //Path path = Paths.get(file.getAbsolutePath());
        //ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        File file = new File(audioPropiedades.getRuta()+"/install.log");
        
    FileSystemResource resource = new FileSystemResource(file);
    
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
}
    
}//end AuthController
