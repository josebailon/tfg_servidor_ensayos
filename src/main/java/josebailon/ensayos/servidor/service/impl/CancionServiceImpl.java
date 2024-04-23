/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.service.impl;

import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Cancion;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.CancionRepository;
import josebailon.ensayos.servidor.repository.GrupoRepository;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import josebailon.ensayos.servidor.service.ICancionService;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */

@Service
@RequiredArgsConstructor

public class CancionServiceImpl implements ICancionService{
    
        private final UsuarioRepository repositorioUsuario;
        private final GrupoRepository repositorioGrupo;
        private final CancionRepository repositorioCancion;

    @Override
    @Transactional
    public Cancion create(UUID idCancion, String nombre, String descripcion, int duracion, int version, UUID idGrupo, Long idUsuario)throws ResponseStatusException {
            Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
            Optional<Grupo> grupo = repositorioGrupo.findById(idGrupo);
            if (usuario.isPresent() && grupo.isPresent()){
                Usuario u = usuario.get();
                Grupo g = grupo.get();
                Cancion c = new Cancion();
                if(permitido(u,g)){
                    c.setId(idCancion);
                    c.setNombre(nombre);
                    c.setDescripcion(descripcion);
                    c.setDuracion(duracion);
                    c.setVersion(version+1);
                    c.setBorrado(false);
                    c.setGrupo(g);
                    return repositorioCancion.save(c);
                }else{
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
                }
            }
            else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
            }
    }

    @Override
    public Cancion edit(Cancion request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Cancion> cancion = repositorioCancion.findById(request.getId());
        if (usuario.isPresent() && cancion.isPresent() && !cancion.get().isBorrado()){
            Usuario u= usuario.get();
            Cancion c= cancion.get();
            if(permitido(u,c)){
                if (request.getVersion()==c.getVersion()){
                    c.setVersion(request.getVersion()+1);
                    c.setNombre(request.getNombre());
                    c.setDescripcion(request.getDescripcion());
                    c.setDuracion(request.getDuracion());
                  return repositorioCancion.save(c);
                }
                else{
                  throw new VersionIncorrectaException("",c);
                }
            }else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
        }
    }

   
    @Override
    public Cancion delete(Cancion request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException{
         Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Cancion> cancion = repositorioCancion.findById(request.getId());
        if (usuario.isPresent() && cancion.isPresent()&& !cancion.get().isBorrado()){
            Usuario u= usuario.get();
            Cancion c= cancion.get();
            if(permitido(u,c)){
                if (request.getVersion()==c.getVersion()){
                    c.setVersion(c.getVersion()+1);
                    c.setBorrado(true);
                  return repositorioCancion.save(c);
                }
                else{
                  throw new VersionIncorrectaException("",c);
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
    
    public boolean permitido(Usuario u, Cancion c){
        return permitido(u,c.getGrupo());
    }
}//end UserService
