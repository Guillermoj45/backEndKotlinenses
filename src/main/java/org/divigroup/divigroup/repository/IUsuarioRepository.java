package org.divigroup.divigroup.repository;


import org.divigroup.divigroup.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
