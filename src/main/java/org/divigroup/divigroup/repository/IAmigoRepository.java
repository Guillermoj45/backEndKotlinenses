package org.divigroup.divigroup.repository;

import org.divigroup.divigroup.model.Amigo;
import org.divigroup.divigroup.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAmigoRepository extends JpaRepository<Amigo, Integer> {
    /**
     * Lista todos los amigos de un usuario
     * @param usuario El usuario sobre el que queremos hacer la consulta
     * @return La lista de amigos de ese usuario
     */
    @Query("SELECT a " +
            "from Amigo a " +
            "where (a.amigo = :usuario or a.user = :usuario) and a.confirmado = true")
    public List<Amigo> amigos(Usuario usuario);

    // @Query(value = """
    //         select *
    //         from divigroup.usuario
    //         where id in (SELECT CASE WHEN a.usuario1 = :idUsuario THEN a.usuario2 ELSE a.usuario1 END as amigo
    //                      FROM divigroup.amigo a
    //                      WHERE a.usuario2 = :idUsuario
    //                         OR a.usuario1 = :idUsuario
    //         )
    //         """, nativeQuery = true)
    // public List<Usuario> pruebas(int idUsuario);
}
