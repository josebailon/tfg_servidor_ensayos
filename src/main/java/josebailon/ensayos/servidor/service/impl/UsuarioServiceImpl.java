/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.service.impl;

import java.util.Optional;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.repository.UsuarioRepository;
import josebailon.ensayos.servidor.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */

@Service
@RequiredArgsConstructor

public class UsuarioServiceImpl implements IUsuarioService{
    
        private final UsuarioRepository repositorio;

    
    
    public Optional<Usuario> findByEmail(String email){
        return repositorio.findByEmail(email);
    }

    @Override
    public Optional<Usuario> findById(Long idUsuario) {
        return repositorio.findById(idUsuario);
    }
}//end UserService
