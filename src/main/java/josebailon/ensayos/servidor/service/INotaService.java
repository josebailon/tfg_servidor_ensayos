/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Nota;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public interface INotaService {
    public Nota create(   UUID idNota,String nombre, String texto, String audio, int version, UUID idCancion, Long idUsuario)throws ResponseStatusException;

    public Nota edit(Nota request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;

    public Nota delete(Nota request, Long idUsuario)throws ResponseStatusException, VersionIncorrectaException;
}
