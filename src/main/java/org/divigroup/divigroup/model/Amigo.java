package org.divigroup.divigroup.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "amigo", schema = "divigroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Amigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario1")
    private Usuario user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario2")
    private Usuario amigo;

    @Column(name = "confimado")
    private boolean confirmado;
}
