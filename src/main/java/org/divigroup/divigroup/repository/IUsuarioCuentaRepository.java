package org.divigroup.divigroup.repository;

import org.divigroup.divigroup.model.Cuenta;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.model.UsuarioCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioCuentaRepository extends JpaRepository<UsuarioCuenta, Integer> {

    /**
     * Te recupera la cuenta a partir de un usuario y una cuenta
     * @param usuario el usuario que debe de tener
     * @param cuenta la cuenta en la que debería estar la cuenta
     * @return un objeto Usuario cuenta opcional
     */
    Optional<UsuarioCuenta> findTopByUsuarioEqualsAndCuentaEquals(Usuario usuario, Cuenta cuenta);


    /**
     * Te devuelve una lista con todos los UsuariosCuenta donde la cuenta sea una en específica
     * @param cuenta La cuenta por la que queremos buscar
     * @return nos devuelve una lista de UsuarioCuenta con todos los elementos en question
     */
    List<UsuarioCuenta> findAllByCuentaEquals(Cuenta cuenta);

    /**
     * Te devuelve una lista con todas las cuentas que tiene un usuario
     * @param usuario El usuario por el que queremos buscar
     * @return nos devuelve una lista de UsuarioCuenta con todos los elementos en question
     */
    @Query("SELECT uc.cuenta " +
            "from UsuarioCuenta uc " +
            "where uc.usuario = :usuario")
    List<Cuenta> listarCuentasUsuario(Usuario usuario);
}
