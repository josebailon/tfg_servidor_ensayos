/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.GrupoRepository;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import josebailon.ensayos.servidor.service.IGrupoService;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */

@Service
@RequiredArgsConstructor

public class GrupoServiceImpl implements IGrupoService{
    
        private final UsuarioRepository repositorioUsuario;
        private final GrupoRepository repositorioGrupo;

    @Override
    @Transactional
    public Grupo create(UUID id, String nombre, String descripcion, int version, Long usuario_id) {
            Optional<Usuario> usuario = repositorioUsuario.findById(usuario_id);
            if (usuario.isPresent()){
                Usuario u = usuario.get();
                Grupo g = new Grupo();
                g.setId(id);
                g.setNombre(nombre);
                g.setDescripcion(descripcion);
                g.setVersion(version);
                g.setBorrado(false);
                g.getUsuarios().add(usuario.get());
                u.getGrupos().add(g);
                repositorioGrupo.save(g);
                repositorioUsuario.save(u);
                return g;
            }
            else {
                return null;
            }
    }

    @Override
    public Grupo edit(Grupo request, Long userId) throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(userId);
        Optional<Grupo> grupo = repositorioGrupo.findById(request.getId());
        if (usuario.isPresent() && grupo.isPresent()){
            Usuario u= usuario.get();
            Grupo g= grupo.get();
            if(permitido(u,g)){
                if (request.getVersion()==g.getVersion()){
                    request.setVersion(request.getVersion()+1);
                  return repositorioGrupo.save(request);
                }
                else{
                  throw new VersionIncorrectaException("",g);
                }
            }else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
        }
    }

   


    
    /**
     * Devuelve si el usuario u puede acceder al grupo
     * @param u
     * @param g
     * @return 
     */
    public boolean permitido(Usuario u, Grupo g){
        return u.getGrupos().contains(g);
    }
       
    
}//end UserService
