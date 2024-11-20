package org.divigroup.divigroup.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cuenta", schema = "proyectokotlin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cuenta {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 20, nullable = false)
    String nombre;

    @Column(name = "descripcion", length = 400, nullable = false)
    String descripcion;

    @Column(name = "imagen", unique = true, length = 200, nullable = false)
    String imagen;

    @Column(name = "imagen_fondo", unique = true, length = 200)
    String imagenFondo;

    public Cuenta (String nombre, String descripcion, String imagen, String imagenFondo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.imagenFondo = imagenFondo;
    }
}
