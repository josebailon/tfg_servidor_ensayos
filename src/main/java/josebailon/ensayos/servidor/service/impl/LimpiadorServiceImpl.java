/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import josebailon.ensayos.servidor.model.entity.Audio;
import josebailon.ensayos.servidor.service.IAudioService;
import josebailon.ensayos.servidor.service.ILimpiadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementacion del servicio de limpieza de almacenamiento
 * @author Jose Javier Bailon Ortiz
 */
@Service
@RequiredArgsConstructor
public class LimpiadorServiceImpl implements ILimpiadorService{

    private final IAudioService audioService;
    Logger logger = Logger.getLogger(LimpiadorServiceImpl.class.getName());
    
    @Override
    public void limpiarAlmacenamiento() {
        List<File> archivos = audioService.getAllArchivoAudio();
        List<Audio> audios = audioService.getAllAudio();
        for (File archivo : archivos) {
            boolean existeEnBD = audios.stream()
                    .filter((a) -> {
                        boolean res = a.getNombreArchivo().equals(archivo.getName());
                        return res;
                    })
                    .findFirst()
                    .isPresent();
            if (!existeEnBD)
                try {
                //borrar si no existe en la base de datos y tiene una edad mayor a 5 minutos    
                Instant fechaMod = Instant.ofEpochMilli(archivo.lastModified());
                if (fechaMod.isBefore(Instant.now().minus(5,ChronoUnit.MINUTES)))
                    audioService.eliminaArchivoAudio(archivo.getName());
            } catch (IllegalStateException | IOException ex) {
                logger.log(Level.WARNING, "No se puede eliminar el archivo {0}", archivo.getName());
            }
        }
                
    }

}//end LimpiadorServiceImpl
