/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import java.util.List;
import java.util.Optional;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.entity.Usuario;

/**
 * Interfaz del servicio de acciones de usuarios
 * 
 * @author Jose Javier Bailon Ortiz
 */
public interface IUsuarioService {
    
    /**
     * Encontrar un usuario por email
     * @param email El email del usuario
     * @return  El usuario 
     */
    public Optional<Usuario> findByEmail(String email);
    
    /**
     * Encontrar un usuario por id
     * @param idUsuario id del usuario
     * @return  El usuario
     */
    public Optional<Usuario> findById(Long idUsuario);
    
    /**
     * Listado de grupos a los que tiene acceso un usuario
     * @param idUsuario Id del usuario
     * @return  El listado de grupos
     */
    public List<Grupo> getGruposCompletos(Long idUsuario);
}
