/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.repository;

import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Audio;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public interface AudioRepository  extends CrudRepository<Audio, UUID>{
}//end UsuarioRepository
