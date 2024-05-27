/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Cancion;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import org.springframework.web.server.ResponseStatusException;

/**
 * Interfaz del servicio de canciones
 * 
 * @author Jose Javier Bailon Ortiz
 */
public interface ICancionService {
    
    /**
     * Crear cancion
     * @param request Cancion
     * @param idGrupo UUID del grupo al que asignarla
     * @param idUsuario Id del usuario que hace la peticion
     * @return La cancion creada
     * @throws ResponseStatusException  Si no se tienen permisos
     */
    public Cancion create(   Cancion request, UUID idGrupo, Long idUsuario)throws ResponseStatusException;

    /**
     * Editar cancion
     * @param request Cancion a editar
     * @param idUsuario Id del usuario que hace la peticion
     * @return La cancion editada
     * @throws ResponseStatusException Si no se tienen permisos
     * @throws VersionIncorrectaException  Si la version es incorrecta
     */
    public Cancion edit(Cancion request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;
    
    
    /**
     * Borrar cancion
     * @param idCancion UUid de la cancion
     * @param idUsuario Id del usuario que hace la peticion
     * @throws ResponseStatusException Si no se tienen permisos
     * @throws VersionIncorrectaException 
     */
    public void delete(UUID idCancion, Long idUsuario)throws ResponseStatusException, VersionIncorrectaException;
}
