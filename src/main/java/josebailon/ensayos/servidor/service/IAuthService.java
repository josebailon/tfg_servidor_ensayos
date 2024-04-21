/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import josebailon.ensayos.servidor.model.LoginResponse;
import josebailon.ensayos.servidor.model.UsuarioEntity;
import josebailon.ensayos.servidor.service.exception.DuplicatedEmailException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public interface IAuthService {
    public LoginResponse intentoLogin(String email, String password);
    public UsuarioEntity registrar(String email, String password) throws DuplicatedEmailException;
}
