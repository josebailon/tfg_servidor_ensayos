/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.service;

import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Cancion;
import josebailon.ensayos.servidor.service.exception.VersionIncorrectaException;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public interface ICancionService {
    public Cancion create(   UUID idCancion,String nombre, String descripcion, int duracion, int version, UUID idGrupo, Long idUsuario)throws ResponseStatusException;

    public Cancion edit(Cancion request, Long idUsuario) throws ResponseStatusException, VersionIncorrectaException;

    public void delete(Cancion request, Long idUsuario)throws ResponseStatusException, VersionIncorrectaException;
}
