package org.divigroup.divigroup.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notificacion", schema = "divigroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "visto")
    private boolean visto;

    @JoinColumn(name = "users_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;
}
