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
 *
 * @author Jose Javier Bailon Ortiz
 */
@Component
@RequiredArgsConstructor
public class TareasProgramadas {
    private Logger logger = Logger.getLogger(TareasProgramadas.class.getName());

    private final ILimpiadorService limpiadorService;
    
    @Scheduled(cron =" 0 */5 * * * *")
    public void limpiarAlmacenamiento(){
        logger.log(Level.INFO, "Limpieza iniciada");
        limpiadorService.limpiarAlmacenamiento();
        logger.log(Level.INFO, "Limpieza finalizada");
    }
}//end TareasProgramadas
