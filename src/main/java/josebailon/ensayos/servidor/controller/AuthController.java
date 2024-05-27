/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import jakarta.validation.Valid;
import josebailon.ensayos.servidor.model.auth.LoginRequest;
import josebailon.ensayos.servidor.model.auth.LoginResponse;
import josebailon.ensayos.servidor.model.auth.RegistrarRequest;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.service.IAuthService;
import josebailon.ensayos.servidor.service.exception.DuplicatedEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de endpoints para login y registro
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    /**
     * Servicio de autentificacion
     */
    private final IAuthService authService;

    /**
     * Endpoint para login
     * 
     * @param request Request con formato JSON con los campos email y password indicando los datos del login del usuario
     * @return 
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request){
        return authService.intentoLogin(request.getEmail(), request.getPassword());
    }
    

    /**
     * Endpoint para registro de un usuario
     * @param request Request con formato JSON con los campos email y password indicando los datos del registro del usuario
     * @return Response con el token de acceso
     * 
     * @throws DuplicatedEmailException Si el email introducido ya esta en uso
     */
    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody @Valid RegistrarRequest request) throws DuplicatedEmailException{
        return authService.registrar(request.getEmail(),request.getPassword());
    }
    
    
}//end AuthController
