/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Nota;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import org.springframework.web.server.ResponseStatusException;

/**
 * Interfaz del servicio de notas
 * 
 * @author Jose Javier Bailon Ortiz
 */
public interface INotaService {
    
    /**
     * Crear nota
     * @param request Nota
     * @param idCancion UUID de la cancion a la que asignar la nota
     * @param idUsuario Id del usuario que hace la peticion
     * @return La nota creada
     * @throws ResponseStatusException si no se tiene permiso
     */
    public Nota create(  Nota request, UUID idCancion, Long idUsuario)throws ResponseStatusException;

    /**
     * Editar una nota
     * @param request Nota
     * @param idUsuario Id del usuario que hace la peticion
     * @return La nota editada
     * @throws ResponseStatusException Si no se tienen permisos
     * @throws VersionIncorrectaException  Si la version no es la correcta
     */
    public Nota edit(Nota request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;
    
    /**
     *  Eliminar una nota
     * @param idNota UUID de la nota eliminar
     * @param idUsuario Id del usuario que hace la peticion
     * @throws ResponseStatusException Si no se tienen permisos
     * @throws VersionIncorrectaException 
     */
    public void delete(UUID idNota, Long idUsuario)throws ResponseStatusException, VersionIncorrectaException;
}
