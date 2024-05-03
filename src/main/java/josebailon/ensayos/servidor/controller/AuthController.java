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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final IAuthService authService;
    
    //Anotaciones
    //RequestBody transforma el cuerpo de la peticion http al objeto java
    //Validated valida la request segun las anotaciones del tipo de objeto (en este caso LoginRequest)
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request){
        return authService.intentoLogin(request.getEmail(), request.getPassword());
    }
    
    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody @Valid RegistrarRequest request) throws DuplicatedEmailException{
        return authService.registrar(request.getEmail(),request.getPassword());
    }
    
    
}//end AuthController
