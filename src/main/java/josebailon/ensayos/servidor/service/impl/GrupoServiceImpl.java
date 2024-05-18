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

public class GrupoServiceImpl implements IGrupoService {

    private final ResolutorPermisos resolutorPermisos;
    private final UsuarioRepository repositorioUsuario;
    private final GrupoRepository repositorioGrupo;

    @Override
    @Transactional
    public Grupo create(Grupo grupo, Long idUsuario) {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Grupo> grupoLocal = repositorioGrupo.findById(grupo.getId());
        //grupo ya existente
        if (grupoLocal.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if (usuario.isPresent()) {

            Usuario u = usuario.get();
            grupo.setVersion(grupo.getVersion()+1);
            grupo.getUsuarios().add(u);
            u.getGrupos().add(grupo);
            repositorioGrupo.save(grupo);
            repositorioUsuario.save(u);
            return grupo;
        } else {
            return null;
        }
    }

    @Override
    public Grupo edit(Grupo request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Grupo> grupo = repositorioGrupo.findById(request.getId());
        if (usuario.isPresent() && grupo.isPresent()) {
            Usuario u = usuario.get();
            Grupo g = grupo.get();
            if (resolutorPermisos.permitido(u, g)) {
                if (request.getVersion() == g.getVersion()) {
                    g.setVersion(request.getVersion() + 1);
                    g.setNombre(request.getNombre());
                    g.setDescripcion(request.getDescripcion());
                    g.setFecha(request.getFecha());
                    return repositorioGrupo.save(g);
                } else {
                    throw new VersionIncorrectaException("", g);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void delete(UUID idgrupo, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Grupo> grupo = repositorioGrupo.findById(idgrupo);
        if (usuario.isPresent() && grupo.isPresent()) {
            Usuario u = usuario.get();
            Grupo g = grupo.get();
            if (resolutorPermisos.permitido(u, g)) {
                    repositorioGrupo.deleteById(g.getId());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Agrega un usuario al grupo
     *
     * @param idGrupo
     * @param emailUsuario
     * @param idUsuario
     * @return
     */
    @Override
    public Grupo deleteUsuario(UUID idGrupo, String emailUsuario, Long idUsuario) {
        System.out.println("Inicio de borrado");

        Optional<Usuario> usuarioDestino = repositorioUsuario.findByEmail(emailUsuario);
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Grupo> grupo = repositorioGrupo.findById(idGrupo);
        if (usuarioDestino.isPresent() && usuario.isPresent() && grupo.isPresent()) {
            System.out.println("Elementos presentes");
            Usuario u = usuario.get();
            Grupo g = grupo.get();
            Usuario uDestino = usuarioDestino.get();
            //comprobar permisos
            if (resolutorPermisos.permitido(u, g)) {
                //si el usuario borrado no esta entonces simplemente devolver el grupo tal cual esta
                if (!g.getUsuarios().contains(uDestino)) {
                    return g;
                }
                //Desasignar usuario    
                uDestino.getGrupos().remove(g);
                g.getUsuarios().remove(uDestino);
                repositorioUsuario.save(uDestino);
                repositorioGrupo.save(g);
                return g;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Grupo addUsuario(UUID idGrupo, String emailUsuario, Long idUsuario) {
        Optional<Usuario> usuarioDestino = repositorioUsuario.findByEmail(emailUsuario);
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        Optional<Grupo> grupo = repositorioGrupo.findById(idGrupo);
        if (usuarioDestino.isPresent() && usuario.isPresent() && grupo.isPresent()) {
            Usuario u = usuario.get();
            Grupo g = grupo.get();
            Usuario uDestino = usuarioDestino.get();
            if (resolutorPermisos.permitido(u, g)) {
                g.getUsuarios().add(uDestino);
                return repositorioGrupo.save(g);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}//end UserService
