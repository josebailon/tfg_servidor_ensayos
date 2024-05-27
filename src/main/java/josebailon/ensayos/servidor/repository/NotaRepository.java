/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.repository;

import java.util.UUID;
import josebailon.ensayos.servidor.model.entity.Nota;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio CRUD de acceso a las notas en la base de datos
 * @author Jose Javier Bailon Ortiz
 */
public interface NotaRepository  extends CrudRepository<Nota, UUID>{
}//end UsuarioRepository
