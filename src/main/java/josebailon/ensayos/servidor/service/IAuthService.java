/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import josebailon.ensayos.servidor.model.auth.LoginResponse;
import josebailon.ensayos.servidor.model.entity.Usuario;
import josebailon.ensayos.servidor.service.exception.DuplicatedEmailException;

/**
 * Interfaz del servicio de autentificacion
 * @author Jose Javier Bailon Ortiz
 */
public interface IAuthService {
    
    /**
     * Realizar login
     * @param email Email del usuario
     * @param password Password delusuario
     * @return  Respuesta del intento de login
     */
    public LoginResponse intentoLogin(String email, String password);
    
    /**
     * Registrar un usuario
     * @param email Email a registrar
     * @param password Password a registrar
     * @return Usuario registrado
     * @throws DuplicatedEmailException  Si el email ya esta en uso
     */
    public Usuario registrar(String email, String password) throws DuplicatedEmailException;
}
