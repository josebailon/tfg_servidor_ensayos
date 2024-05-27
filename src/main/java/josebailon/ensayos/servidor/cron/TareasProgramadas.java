/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.cron;

import java.util.logging.Level;
import java.util.logging.Logger;
import josebailon.ensayos.servidor.service.ILimpiadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Clase para especificar tareas programadas
 * @author Jose Javier Bailon Ortiz
 */
@Component
@RequiredArgsConstructor
public class TareasProgramadas {

    private Logger logger = Logger.getLogger(TareasProgramadas.class.getName());
    
    /**
     * Servicio para limpieza de almacenamiento
     * */
    private final ILimpiadorService limpiadorService;
    
    /**
     * Cron para lanzar la limpieza del almacenamiento cada 5 minutos.
     */
    @Scheduled(cron =" 0 */5 * * * *")
    public void limpiarAlmacenamiento(){
        logger.log(Level.INFO, "Limpieza iniciada");
        limpiadorService.limpiarAlmacenamiento();
        logger.log(Level.INFO, "Limpieza finalizada");
    }
}//end TareasProgramadas
