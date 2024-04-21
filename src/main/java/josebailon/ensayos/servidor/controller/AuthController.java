/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import josebailon.ensayos.servidor.model.LoginRequest;
import josebailon.ensayos.servidor.model.LoginResponse;
import josebailon.ensayos.servidor.model.RegistrarRequest;
import josebailon.ensayos.servidor.model.UsuarioEntity;
import josebailon.ensayos.servidor.service.IAuthService;
import josebailon.ensayos.servidor.service.exception.DuplicatedEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@RestController
@RequiredArgsConstructor
public class AuthController {
    
    private final IAuthService authService;
    
    //Anotaciones
    //RequestBody transforma el cuerpo de la peticion http al objeto java
    //Validated valida la request segun las anotaciones del tipo de objeto (en este caso LoginRequest)
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        return authService.intentoLogin(request.getEmail(), request.getPassword());
    }
    
    @PutMapping("/auth/registrar")
    public UsuarioEntity registrar(@RequestBody @Validated RegistrarRequest request) throws DuplicatedEmailException{
        return authService.registrar(request.getEmail(),request.getPassword());
    }
    
    
}//end AuthController
