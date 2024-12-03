package org.divigroup.divigroup.model;

import jakarta.persistence.*;
import lombok.*;
import org.divigroup.divigroup.model.enums.TipoPago;

@Entity
@Table(name = "historial_pago", schema = "divigroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HistorialPago {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipo_pago")
    @Enumerated(EnumType.ORDINAL)
    private TipoPago tipoPago;

    @Column(name = "monton")
    private float monton;

    @JoinColumn(name = "id_users")
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(name = "id_cuenta")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cuenta cuenta;
}
