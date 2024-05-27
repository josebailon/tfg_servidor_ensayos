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
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;
import josebailon.ensayos.servidor.model.vistas.Vista;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad de audio para la base de datos
 * 
 * @author Jose Javier Bailon Ortiz
 */
@Entity
@Getter
@Setter
public class Audio {
    
    @Id
    @JsonView(Vista.Esencial.class)
    @NotNull
    private UUID id;
    @JsonView(Vista.Esencial.class)
    @NotEmpty
    private String nombreArchivo;
    @JsonView(Vista.Esencial.class)
    @NotNull
    private int version;
    @JsonView(Vista.Esencial.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date fecha;
    @OneToOne
    @MapsId
    @JsonIgnore
    private Nota nota;

    @Override
    public String toString() {
        return "Audio{" + "id=" + id + ", nombreArchivo=" + nombreArchivo + ", version=" + version +  '}';
    }

    

    
    
    
}//end UsuarioEntity
