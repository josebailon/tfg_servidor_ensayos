/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import josebailon.ensayos.servidor.model.vistas.Vista;
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
    @JsonView(Vista.Esencial.class)
    private UUID id;
    @JsonView(Vista.Esencial.class)
    private String nombre;
    @JsonView(Vista.Esencial.class)
    private String descripcion;
    @JsonView(Vista.Esencial.class)
    private int version;


    @ManyToMany(mappedBy = "grupos")
    @JsonView(Vista.Esencial.class)
    private Set<Usuario> usuarios = new HashSet<>();

    @OneToMany(mappedBy="grupo", cascade = CascadeType.REMOVE)
    @JsonView(Vista.Completa.class)
    private Set<Cancion> canciones;
    
    @Override
    public String toString() {
        return "Grupo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", version=" + version + '}';
    }
    
    
}//end UsuarioEntity
