/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Audio;
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
    public Audio create(   Audio request, MultipartFile archivo, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;
    public Audio edit(Audio request, MultipartFile archivo, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;
    public void delete(UUID idAudio, Long idUsuario)throws ResponseStatusException, VersionIncorrectaException;
    public ResponseEntity<Resource> get(UUID idAudio, Long idUsuario);
    public boolean eliminaArchivoAudio(String nombre) throws IllegalStateException, IOException;
    public List<File> getAllArchivoAudio();
    public List<Audio> getAllAudio();
 }
