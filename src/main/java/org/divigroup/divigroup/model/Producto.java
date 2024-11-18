package org.divigroup.divigroup.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "producto", schema = "divigroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Producto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 100)
    String nombre;

    @Column(name = "descripcion", length = 400)
    String descripcion;

    @Column(name = "precio")
    private float precio;

    @Column(name = "imagen", unique = true, length = 200)
    private String imagen;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "facturas", length = 200)
    private String factura;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_users")
    private Usuario user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;
}
