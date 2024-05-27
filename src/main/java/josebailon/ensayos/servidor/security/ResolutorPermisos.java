/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.security;

import josebailon.ensayos.servidor.model.entity.Audio;
import josebailon.ensayos.servidor.model.entity.Cancion;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.entity.Nota;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.CancionRepository;
import josebailon.ensayos.servidor.repository.GrupoRepository;
import josebailon.ensayos.servidor.repository.NotaRepository;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * Resuelve si dado un usuario tiene permisos de acceso a una entidad
 *
 * @author Jose Javier Bailon Ortiz
 */
@Component
@RequiredArgsConstructor
public class ResolutorPermisos {
    
    /**
     * Repositorio de usuarios
     */
    private final UsuarioRepository repositorioUsuario;
    
    /**
     * Repositorio de grupos
     */
    private final GrupoRepository repositorioGrupo;
    
    /**
     * Repositorio de canciones
     */
    private final CancionRepository repositorioCancion;
    
    /**
     * Repositorio de notas
     */
    private final NotaRepository repositorioNota;

    
    /**
     *  Devuelve si un usuario tiene permiso de acceso
     * 
     * @param u Usuario 
     * @param g Grupo
     * @return  True si tiene permiso de acceso
     */
    public boolean permitido(Usuario u, Grupo g) {
        return u.getGrupos().contains(g);
    }

    /**
     * Devuelve si un usuario tiene permiso de acceso
     * @param u Usuario
     * @param c Cancion
     * @return  True si tiene permiso de acceso
     */
    public boolean permitido(Usuario u, Cancion c) {
        return permitido(u, c.getGrupo());
    }

    
     /**
     * Devuelve si un usuario tiene permiso de acceso
     * @param u Usuario
     * @param n Nota
     * @return  True si tiene permiso de acceso
     */
    public boolean permitido(Usuario u, Nota n) {
        return permitido(u, n.getCancion());
    }

     /**
     * Devuelve si un usuario tiene permiso de acceso
     * @param u Usuario
     * @param a Audio
     * @return  True si tiene permiso de acceso
     */
    public boolean permitido(Usuario u, Audio a) {
        return permitido(u, a.getNota());
    }
}//end ResolutorPermisos
