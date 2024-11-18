package org.divigroup.divigroup.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionId;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat", schema = "divigroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hora")
    private LocalDateTime hora;

    @Column(name = "mensaje")
    private String mensaje;

    @JoinColumn(name = "id_usuario1")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario emisor;

    @JoinColumn(name = "id_usuario2")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario receptor;
}
