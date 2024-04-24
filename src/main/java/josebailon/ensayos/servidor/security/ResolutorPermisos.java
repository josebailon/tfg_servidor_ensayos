/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import josebailon.ensayos.servidor.model.entity.Cancion;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.entity.Nota;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.CancionRepository;
import josebailon.ensayos.servidor.repository.GrupoRepository;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Component
@RequiredArgsConstructor
public class ResolutorPermisos {
        private final UsuarioRepository repositorioUsuario;
        private final GrupoRepository repositorioGrupo;
        private final CancionRepository repositorioCancion;
        private final CancionRepository repositorioNota;
    
        
    public boolean permitido(Usuario u, Grupo g){
        return u.getGrupos().contains(g);
    }
    
    public boolean permitido(Usuario u, Cancion c){
        return permitido(u,c.getGrupo());
    }
    
    public boolean permitido(Usuario u, Nota n){
        return permitido(u,n.getCancion());
    }
}//end ResolutorPermisos
