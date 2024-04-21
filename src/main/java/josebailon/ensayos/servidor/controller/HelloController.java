/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.controller;

import java.util.List;
import josebailon.ensayos.servidor.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */

//Agregar comportamiento de controlador MVC convirtiendo todas las respuestas a JSON
@RestController 
//Generar automaticamente el constructor
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String greeting(){
        return "hola mundo";
    }
    
    @GetMapping("/otro")
    public List<String> hola(@AuthenticationPrincipal UserPrincipal principal){
        List<String> lista = List.of("uno","dos","tres");
        return lista;
        
    }
    
    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal){
        return "Si lo ves estas autenticado como "+principal.getEmail()+" User id:"+principal.getUserId();
    }
}//end HelloController
