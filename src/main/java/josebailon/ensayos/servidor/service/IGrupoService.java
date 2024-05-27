/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import org.springframework.web.server.ResponseStatusException;

/**
 * Interfaz de servicio de grupos
 * @author Jose Javier Bailon Ortiz
 */
public interface IGrupoService {
    
    /**
     * Crear grupo
     * @param request Grupo
     * @param idUsuario Id del usuario que hace la peticion
     * @return El grupo creado
     */
    public Grupo create(   Grupo request, Long idUsuario);

    /**
     * Editar grupo
     * @param request El grupo
     * @param idUsuario Id del usuario que hace la peticion
     * @return El grupo creado
     * @throws ResponseStatusException Si no se tineen permisos
     * @throws VersionIncorrectaException  Si la version es incorrecta
     */
    public Grupo edit(Grupo request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;

    
    /**
     * Eliminar un grupo
     * @param idGrupo UUID del grupo a eliminar
     * @param idUsuario Id del usuario que hace la peticion
     * @throws ResponseStatusException Si no se tienen permisos
     * @throws VersionIncorrectaException  
     */
    public void delete(UUID idGrupo, Long idUsuario)throws ResponseStatusException, VersionIncorrectaException;

    /**
     * Agregar un usuario a un grupo
     * @param idGrupo UUID del grupo al que agregar el usuario
     * @param emailusuario Email del usuario a agregar
     * @param idUsuario Id del usuario que hace la peticion
     * @return Grupo modificado
     */
    public Grupo addUsuario(UUID idGrupo, String emailusuario, Long idUsuario);

    /**
     * Eliminar un usuario de un grupo
     * @param idGrupo UUID del grupo del que sacar el usuario
     * @param emailusuario Email del usuario a eliminar
     * @param idUsuario Id del usuario que hace la peticion
     * @return Grupo modificado
     */
    public Grupo deleteUsuario(UUID idGrupo, String emailusuario, Long idUsuario);
}
