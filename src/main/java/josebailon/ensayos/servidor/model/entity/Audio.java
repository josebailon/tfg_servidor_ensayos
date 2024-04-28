/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
public class Audio {
    
    @Id
    @JsonView(Vista.Esencial.class)
    private UUID id;
    @JsonView(Vista.Esencial.class)
    private String nombreArchivo;
    @JsonView(Vista.Esencial.class)
    private int version;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Nota nota;

    @Override
    public String toString() {
        return "Audio{" + "id=" + id + ", nombreArchivo=" + nombreArchivo + ", version=" + version +  '}';
    }

    

    
    
    
}//end UsuarioEntity
