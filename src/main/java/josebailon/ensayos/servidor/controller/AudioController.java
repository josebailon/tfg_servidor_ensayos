/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import josebailon.ensayos.servidor.config.AudioPropiedades;
import josebailon.ensayos.servidor.model.entity.Audio;
import josebailon.ensayos.servidor.model.entity.Cancion;
import josebailon.ensayos.servidor.model.vistas.Vista;
import josebailon.ensayos.servidor.security.UserPrincipal;
import josebailon.ensayos.servidor.service.IAudioService;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequiredArgsConstructor
public class AudioController {
    
    private final IAudioService audioService;
    
    
    @PostMapping("/audio")
    @JsonView(Vista.Esencial.class)
    public Audio create( @RequestPart("datos") Audio request, @RequestPart("archivo") MultipartFile archivo, @AuthenticationPrincipal UserPrincipal principal){
        return audioService.create(request.getId(), request.getVersion(),archivo, principal.getUserId());
    }
    
    
    @PutMapping("/audio")
    @JsonView(Vista.Esencial.class)
    public Audio edit(@RequestPart("datos") Audio request, @RequestPart("file") MultipartFile archivo, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        System.out.println(request);
        return audioService.edit(request, archivo, principal.getUserId());
    }
    
   @DeleteMapping("/audio")
    @JsonView(Vista.Esencial.class)
    public ResponseEntity delete(@RequestBody @Validated Audio request, @AuthenticationPrincipal UserPrincipal principal)throws ResponseStatusException, VersionIncorrectaException{
        audioService.delete(request, principal.getUserId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    
 
// 
//    @GetMapping(path = "/audio")
//public ResponseEntity<Resource> download(String param) throws IOException {
//
//        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=install.log");
//        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        header.add("Pragma", "no-cache");
//        header.add("Expires", "0");
//
//        //Path path = Paths.get(file.getAbsolutePath());
//        //ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//        File file = new File(audioPropiedades.getRuta()+"/install.log");
//        
//    FileSystemResource resource = new FileSystemResource(file);
//    
//        return ResponseEntity.ok()
//                .headers(header)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .body(resource);
//}
//    
}//end AuthController
