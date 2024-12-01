package org.divigroup.divigroup.dto;

import lombok.Getter;
import lombok.Setter;
import org.divigroup.divigroup.model.Usuario;

import java.util.ArrayList;
import java.util.Set;

@Getter
@Setter
public class AgregarCuentaDTO {
    private String nombre;
    private String descripcion;
    private Float saldo;
    private String imagen;
    private String imagenFondo;
    private Set<Usuario> personas;
}
