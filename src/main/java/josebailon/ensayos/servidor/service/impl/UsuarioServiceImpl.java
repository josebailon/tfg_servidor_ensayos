/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.service.impl;

import java.util.Optional;
import josebailon.ensayos.servidor.model.UsuarioEntity;
import josebailon.ensayos.servidor.service.IUsuarioService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    private static final String EXISTING_EMAIL = "test@test.com";
    
    public Optional<UsuarioEntity> findByEmail(String email){
        if (! EXISTING_EMAIL.equalsIgnoreCase(email)) return Optional.empty();
        
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(1L);
        usuario.setEmail(EXISTING_EMAIL);
        usuario.setPassword("$2a$12$OBnerD3ZrnkqY/ofkaxune1jnpUscFhTGCcuVA9x5lgAGAtr6Bss2");
        usuario.setRole("ROLE_ADMIN");
        return Optional.of(usuario);
    }
}//end UserService
