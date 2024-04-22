/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Entity
@Getter
@Setter
public class Grupo {
    
    @Id
    private UUID id;
    
    private String nombre;

    private String descripcion;
    
    private int version;
    
    private boolean borrado;

    @ManyToMany(mappedBy = "grupos")
    private Set<Usuario> usuarios = new HashSet<>();
    
    
}//end UsuarioEntity
