package org.divigroup.divigroup.repository;


import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.model.enums.Rol;
import org.divigroup.divigroup.model.enums.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("""
    select u.id
    from Usuario u
    where u.username = :userName
    """)
    Optional<Integer> buscarNombre(String userName);
}
