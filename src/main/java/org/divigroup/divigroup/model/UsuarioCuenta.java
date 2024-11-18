package org.divigroup.divigroup.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_cuenta", schema = "divigroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsuarioCuenta {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

    @Column(name = "is_admin")
    private boolean isAdmin;

    // @Column(name = "ajustes")
    // private Json ajustes;
}
