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
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Nota {
    
    @Id
    @JsonView(Vista.Esencial.class)
    @NotNull
    private UUID id;
    @JsonView(Vista.Esencial.class)
    @NotEmpty
    private String nombre;
    @JsonView(Vista.Esencial.class)
    @NotNull
    @Column(columnDefinition="TEXT")
    private String texto;
    @JsonView(Vista.Esencial.class)
    @NotNull
    private int version;

    
    @OneToOne(mappedBy="nota", cascade = CascadeType.ALL, optional=true, fetch = FetchType.LAZY)
    @JsonView(Vista.Completa.class)
    private Audio audio;
    
    @ManyToOne
    @JoinColumn(name="cancion_id", nullable=false)
    @JsonIgnore
    private Cancion cancion;

    @Override
    public String toString() {
        return "Nota{" + "id=" + id + ", nombre=" + nombre + ", texto=" + texto + ", audio=" + audio + ", version=" + version + ", cancion=" + cancion.getId() + '}';
    }

    
    
    
}//end UsuarioEntity
