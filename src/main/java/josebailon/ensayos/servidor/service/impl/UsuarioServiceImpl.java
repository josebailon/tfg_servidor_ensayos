/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import josebailon.ensayos.servidor.model.entity.Grupo;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.GrupoRepository;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import josebailon.ensayos.servidor.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementacion del servicio de usuarios
 * @author Jose Javier Bailon Ortiz
 */

@Service
@RequiredArgsConstructor

public class UsuarioServiceImpl implements IUsuarioService{
    
        private final UsuarioRepository repositorioUsuario;
    
        private Logger logger = Logger.getLogger(UsuarioServiceImpl.class.getName());

      @Override
    public Optional<Usuario> findByEmail(String email){
        return repositorioUsuario.findByEmail(email);
    }

    @Override
    public Optional<Usuario> findById(Long idUsuario) {
        return repositorioUsuario.findById(idUsuario);
    }

    @Override
    public List<Grupo> getGruposCompletos(Long idUsuario) {
        Optional<Usuario> usuario = repositorioUsuario.findById(idUsuario);
        if (usuario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
        else{
            logger.log(Level.INFO,"Usuario descarga completa de datos: {0}",idUsuario);
            return usuario.get().getGrupos().stream().collect(Collectors.toList());
        }
    }
    
    
}//end UserService
