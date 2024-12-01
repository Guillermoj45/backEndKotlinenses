package org.divigroup.divigroup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.divigroup.divigroup.model.Usuario;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuentaDataDTO {
    private Integer id;

    String nombre;

    String descripcion;

    String imagen;

    String imagenFondo;

    Set<Usuario> participantes;

    Float totalGastos;

    public CuentaDataDTO(Integer id, String nombre, String descripcion, String imagen, String imagenFondo, Set<Usuario> participantes, Float totalGastos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.imagenFondo = imagenFondo;
        this.participantes = participantes;
        this.totalGastos = totalGastos;
    }
}
