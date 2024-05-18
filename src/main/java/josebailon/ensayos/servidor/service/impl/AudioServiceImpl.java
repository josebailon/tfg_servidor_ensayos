/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service.impl;

import jakarta.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import josebailon.ensayos.servidor.config.AudioPropiedades;
import josebailon.ensayos.servidor.model.entity.Audio;
import josebailon.ensayos.servidor.model.entity.Nota;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.AudioRepository;
import josebailon.ensayos.servidor.repository.NotaRepository;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import josebailon.ensayos.servidor.security.ResolutorPermisos;
import josebailon.ensayos.servidor.service.IAudioService;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Service
@RequiredArgsConstructor

public class AudioServiceImpl implements IAudioService {

    private final ResolutorPermisos resolutorPermisos;
    private final UsuarioRepository repositorioUsuario;
    private final NotaRepository repositorioNota;
    private final AudioRepository repositorioAudio;
    private final AudioPropiedades audioPropiedades;

    @Override
    @Transactional
    public Audio create(Audio request, MultipartFile archivo, Long idUsuario)  throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Nota> nota = repositorioNota.findById(request.getId());
        Optional<Audio> audioLocal = repositorioAudio.findById(request.getId());
        if (usuario.isPresent()) {

            Usuario u = usuario.get();
            Nota n = nota.get();
            Audio a = new Audio();
            if (resolutorPermisos.permitido(u, n)) {
                
                //diferir a update si ya existe
                if (audioLocal.isPresent()){
                    return this.edit(request, archivo, idUsuario);
                }
                
                
                String nombreArchivo;
                try {
                    nombreArchivo = this.guardaArchivo(archivo);
                    a = request;
//                    a.setId(idAudio);
                    a.setNombreArchivo(nombreArchivo);
                    a.setVersion(request.getVersion() + 1);
                    a.setNota(n);
                    return repositorioAudio.save(a);
                } catch (IllegalStateException | IOException ex) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public Audio edit(Audio request, MultipartFile archivo, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Audio> audio = repositorioAudio.findById(request.getId());
        if (usuario.isPresent() && audio.isPresent()) {
            Usuario u = usuario.get();
            Audio a = audio.get();
            if (resolutorPermisos.permitido(u, a)) {
                if (request.getVersion() == a.getVersion()) {
                    String nombreArchivo;
                    String nombreArchivoAnterior = a.getNombreArchivo();
                    try {
                        nombreArchivo = this.guardaArchivo(archivo);
                        eliminaArchivo(nombreArchivoAnterior);
                        a=request;
                        a.setNombreArchivo(nombreArchivo);
                        a.setVersion(request.getVersion() + 1);
                        return repositorioAudio.save(a);

                    } catch (IllegalStateException | IOException ex) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } else {
                    throw new VersionIncorrectaException("", a);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void delete(UUID idAudio, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Audio> audio = repositorioAudio.findById(idAudio);
        if (usuario.isPresent() && audio.isPresent()) {
            Usuario u = usuario.get();
            Audio a = audio.get();
            if (resolutorPermisos.permitido(u, a)) {
                     try {
                        System.out.println("Nombre archivo " + a.getNombreArchivo() + "   id:" + a.getId());
                        Nota n = a.getNota();
                        n.setAudio(null);
                        repositorioAudio.deleteById(a.getId());
                        this.eliminaArchivo(a.getNombreArchivo());
                    } catch (IllegalStateException | IOException ex) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Resource> get(UUID idAudio, Long idUsuario) {

        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Audio> audio = repositorioAudio.findById(idAudio);
        if (usuario.isPresent() && audio.isPresent()) {
            Usuario u = usuario.get();
            Audio a = audio.get();
            if (resolutorPermisos.permitido(u, a)) {
                
                
                File archivo = null;
                try {
                File rutaAlmacenamiento = new File(audioPropiedades.getRuta() + "/");
                archivo = new File(audioPropiedades.getRuta() + "/" + a.getNombreArchivo());
                    boolean enAlmacenamiento = archivo.getCanonicalPath().startsWith(rutaAlmacenamiento.getCanonicalPath() + File.separator);
                    if (!StringUtils.hasText(a.getNombreArchivo()) || 
                            !archivo.exists() || 
                            archivo.isDirectory() || 
                            !enAlmacenamiento) {
                       throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                   }
                } catch (IOException ex) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }

                FileSystemResource resource = new FileSystemResource(archivo);

                HttpHeaders header = new HttpHeaders();
                header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+a.getNombreArchivo());
                header.add("Cache-Control", "no-cache, no-store, must-revalidate");
                header.add("Pragma", "no-cache");
                header.add("Expires", "0");
                
                return ResponseEntity.ok()
                        .headers(header)
                        .contentLength(archivo.length())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(resource);

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    private String guardaArchivo(MultipartFile archivo) throws IllegalStateException, IOException {

        //comprobar que es un mp3
        if (!archivo.getContentType().equalsIgnoreCase("audio/mpeg")) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        String rutaFinal = audioPropiedades.getRuta() + "/";
        String nombre = UUID.randomUUID() + ".mp3";
        rutaFinal += nombre;
        archivo.transferTo(new File(rutaFinal));
        return nombre;
    }

    private boolean eliminaArchivo(String nombre) throws IllegalStateException, IOException {
        File rutaAlmacenamiento = new File(audioPropiedades.getRuta() + "/");
        File archivo = new File(audioPropiedades.getRuta() + "/" + nombre);
        boolean enAlmacenamiento = archivo.getCanonicalPath().startsWith(rutaAlmacenamiento.getCanonicalPath() + File.separator);
        if (!StringUtils.hasText(nombre) || !archivo.exists() || archivo.isDirectory() || !enAlmacenamiento) {
            return false;
        }
        return archivo.delete();
    }

}//end UserService
