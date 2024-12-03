package org.divigroup.divigroup.dto;


import lombok.Data;


@Data
public class AgregarProducto {
    private String nombre;
    private String descripcion;
    private float precio;
    private String imagen;
}
