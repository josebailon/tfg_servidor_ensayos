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
import josebailon.ensayos.servidor.model.entity.Nota;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.CancionRepository;
import josebailon.ensayos.servidor.repository.GrupoRepository;
import josebailon.ensayos.servidor.repository.NotaRepository;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import josebailon.ensayos.servidor.security.ResolutorPermisos;
import josebailon.ensayos.servidor.service.ICancionService;
import josebailon.ensayos.servidor.service.INotaService;
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

public class NotaServiceImpl implements INotaService{
        private final ResolutorPermisos resolutorPermisos;
        private final UsuarioRepository repositorioUsuario;
        private final GrupoRepository repositorioGrupo;
        private final CancionRepository repositorioCancion;
        private final NotaRepository repositorioNota;

    @Override
    @Transactional
    public Nota create(UUID idNota, String nombre, String texto, int version, UUID idCancion, Long idUsuario)throws ResponseStatusException {
            Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
            Optional<Cancion> cancion = repositorioCancion.findById(idCancion);
            
            if (usuario.isPresent() && cancion.isPresent()){
                Usuario u = usuario.get();
                Cancion c = cancion.get();
                Nota n = new Nota();
                if(resolutorPermisos.permitido(u,c)){
                    n.setId(idNota);
                    n.setNombre(nombre);
                    n.setTexto(texto);
                    n.setVersion(version+1);
                    n.setCancion(c);
                    return repositorioNota.save(n);
                }else{
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
                }
            }
            else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
            }
    }

    @Override
    public Nota edit(Nota request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Nota> nota = repositorioNota.findById(request.getId());
        if (usuario.isPresent() && nota.isPresent()){
            Usuario u= usuario.get();
            Nota n= nota.get();
            if(resolutorPermisos.permitido(u,n)){
                if (request.getVersion()==n.getVersion()){
                    n.setVersion(request.getVersion()+1);
                    n.setNombre(request.getNombre());
                    n.setTexto(request.getTexto());
                    n.setAudio(request.getAudio());
                  return repositorioNota.save(n);
                }
                else{
                  throw new VersionIncorrectaException("",n);
                }
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
        }
    }

   
    @Override
    public void delete(UUID idNota, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException{
         Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Nota> nota = repositorioNota.findById(idNota);
        if (usuario.isPresent() && nota.isPresent()){
            Usuario u= usuario.get();
            Nota n= nota.get();
            if(resolutorPermisos.permitido(u,n)){
                    repositorioNota.deleteById(n.getId());
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
        }
        
        
    }
 
}//end UserService
