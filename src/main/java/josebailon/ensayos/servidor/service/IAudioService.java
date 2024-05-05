/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Audio;
import josebailon.ensayos.servidor.model.entity.Nota;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public interface IAudioService {
    public Audio create(   UUID idAudio, int version, MultipartFile archivo, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;

    public Audio edit(Audio audio, MultipartFile archivo, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;

    public void delete(Audio request, Long idUsuario)throws ResponseStatusException, VersionIncorrectaException;

    public ResponseEntity<Resource> get(UUID idAudio, Long idUsuario);
}
