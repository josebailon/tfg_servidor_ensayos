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
import josebailon.ensayos.servidor.security.ResolutorPermisos;
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
        private final ResolutorPermisos resolutorPermisos;
        private final UsuarioRepository repositorioUsuario;
        private final GrupoRepository repositorioGrupo;

    @Override
    @Transactional
    public Grupo create(UUID idGrupo, String nombre, String descripcion, int version, Long idUsuario) {
            Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
            if (usuario.isPresent()){
                Usuario u = usuario.get();
                Grupo g = new Grupo();
                g.setId(idGrupo);
                g.setNombre(nombre);
                g.setDescripcion(descripcion);
                g.setVersion(version+1);
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
    public Grupo edit(Grupo request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Grupo> grupo = repositorioGrupo.findById(request.getId());
        if (usuario.isPresent() && grupo.isPresent() && !grupo.get().isBorrado()){
            Usuario u= usuario.get();
            Grupo g= grupo.get();
            if(resolutorPermisos.permitido(u,g)){
                if (request.getVersion()==g.getVersion()){
                    g.setVersion(request.getVersion()+1);
                    g.setNombre(request.getNombre());
                    g.setDescripcion(request.getDescripcion());
                  return repositorioGrupo.save(g);
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

   
    @Override
    public Grupo delete(Grupo request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException{
         Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Grupo> grupo = repositorioGrupo.findById(request.getId());
        if (usuario.isPresent() && grupo.isPresent()&& !grupo.get().isBorrado()){
            Usuario u= usuario.get();
            Grupo g= grupo.get();
            if(resolutorPermisos.permitido(u,g)){
                if (request.getVersion()==g.getVersion()){
                    g.setVersion(g.getVersion()+1);
                    g.setBorrado(true);
                  return repositorioGrupo.save(g);
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
     * Agrega un usuario al grupo
     * @param idGrupo
     * @param emailUsuario
     * @param idUsuario
     * @return 
     */
    @Override
    public Grupo deleteUsuario(UUID idGrupo, String emailUsuario, Long idUsuario) {
        Optional<Usuario> usuarioDestino = repositorioUsuario.findByEmail(emailUsuario);
         Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
         Optional<Grupo> grupo = repositorioGrupo.findById(idGrupo);
        if (usuarioDestino.isPresent() && usuario.isPresent() && grupo.isPresent() && !grupo.get().isBorrado()){
            Usuario u= usuario.get();
            Grupo g= grupo.get();
            Usuario uDestino = usuarioDestino.get();
            //comprobar si el grupo tiene mas de un usuario y si el usuario
            //si no lo tiene simplemente devolver el grupo
            if (!g.getUsuarios().contains(uDestino)){
                System.out.println("No lo tiene");
                 return g;
            }            
            if(resolutorPermisos.permitido(u,g)){
                    uDestino.getGrupos().remove(g);
                    g.getUsuarios().remove(uDestino);
                    g.setVersion(g.getVersion()+1);
                    repositorioUsuario.save(uDestino);
                    repositorioGrupo.save(g);
                    
                  return g;
            }else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
        }
    }

    
    @Override
    public Grupo addUsuario(UUID idGrupo, String emailUsuario, Long idUsuario) {
        Optional<Usuario> usuarioDestino = repositorioUsuario.findByEmail(emailUsuario);
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        
        Optional<Grupo> grupo = repositorioGrupo.findById(idGrupo);
        if (usuarioDestino.isPresent() && usuario.isPresent() && grupo.isPresent() && !grupo.get().isBorrado()){
            Usuario u= usuario.get();
            Grupo g= grupo.get();
            Usuario uDestino = usuarioDestino.get();
            if(resolutorPermisos.permitido(u,g)){
                    uDestino.getGrupos().add(g);
                    repositorioUsuario.save(uDestino);
                    g.setVersion(g.getVersion()+1);
                  return repositorioGrupo.save(g);
            }else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
        }    }
  
 



    
}//end UserService
