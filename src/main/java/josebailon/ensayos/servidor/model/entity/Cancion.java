/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import josebailon.ensayos.servidor.model.vistas.Vista;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad de Cancion para la base de datos
 * 
 * @author Jose Javier Bailon Ortiz
 */
@Entity
@Getter
@Setter
public class Cancion {
    
    @Id
    @JsonView(Vista.Esencial.class)
    @NotNull
    private UUID id;
    @JsonView(Vista.Esencial.class)
    @NotEmpty
    private String nombre;
    @JsonView(Vista.Esencial.class)
    @NotNull
    private String descripcion;
    @JsonView(Vista.Esencial.class)
    @NotNull
    private String duracion;
    @JsonView(Vista.Esencial.class)
    @NotNull
    private int version;
    @JsonView(Vista.Esencial.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date fecha;


    @ManyToOne
    @JoinColumn(name="grupo_id", nullable=false)
    @JsonIgnore
    private Grupo grupo;

    @OneToMany(mappedBy="cancion", cascade = CascadeType.ALL)
    @JsonView(Vista.Completa.class)
    private Set<Nota> notas;
    @Override
    public String toString() {
        return "Grupo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", version=" + version + ", grupo="+ grupo.getNombre()+'}';
    }
    
    
}//end UsuarioEntity
