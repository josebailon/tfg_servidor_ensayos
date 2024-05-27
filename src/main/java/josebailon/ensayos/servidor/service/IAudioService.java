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
 * Interfaz del servicio de audios
 * 
 * @author Jose Javier Bailon Ortiz
 */
public interface IAudioService {
    
    /**
     * Crear un audio
     * @param request Audio
     * @param archivo Archivo asociado
     * @param idUsuario Id de usuario que lo solicita
     * @return El audio
     * @throws ResponseStatusException Si no se tiene permisos
     * @throws VersionIncorrectaException  Si la version es incorrecta
     */
    public Audio create(   Audio request, MultipartFile archivo, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;
    
    /**
     * Edita un audio
     * @param request Audio
     * @param archivo Archivo asociado
     * @param idUsuario Id del usuario que lo solicita
     * @return El audio editado
     * @throws ResponseStatusException Si no se tienen permisos
     * @throws VersionIncorrectaException Si la version es incorrecta
     */
    public Audio edit(Audio request, MultipartFile archivo, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;
    
    /**
     * Borra un audio
     * @param idAudio UUID del audio
     * @param idUsuario Id del usuario
     * @throws ResponseStatusException Si no tiene permisos para la eliminacion
     * @throws VersionIncorrectaException
     */
    public void delete(UUID idAudio, Long idUsuario)throws ResponseStatusException, VersionIncorrectaException;
    
    /**
     * Devuelve una respuesta con el archivo asociado al audio
     * @param idAudio UUID del audio
     * @param idUsuario Id del usuario
     * @return  Respuesta con el archivo de audio
     */
    public ResponseEntity<Resource> get(UUID idAudio, Long idUsuario);
    
    /**
     * Elimina un archivo de audio
     * @param nombre Nombre del archivo
     * @return True si lo ha borrado
     * @throws IllegalStateException Si no se puee borrar
     * @throws IOException Si no se puee borrar
     */
    public boolean eliminaArchivoAudio(String nombre) throws IllegalStateException, IOException;
    
    /**
     * Devuelve un listado de archivos de audio existentes
     * @return  El listado
     */
    public List<File> getAllArchivoAudio();
    
    /**
     * Devuelve un listado con todos los audios de la base de datos
     * @return El listado
     */
    public List<Audio> getAllAudio();
 }
