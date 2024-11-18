package org.divigroup.divigroup.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.divigroup.divigroup.model.Cuenta;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.model.UsuarioCuenta;
import org.divigroup.divigroup.repository.IUsuarioCuentaRepository;
import org.divigroup.divigroup.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioCuentaService {

    IUsuarioCuentaRepository usuarioCuentaRepository;

    UsuarioService usuarioService;



    /**
     * Agrega un usuario a una cuenta
     * @param cuenta A la cuenta a la que lo queremos añadir
     * @param usuario El usuarios que queremos añadir
     * @return devuelve la relación entre ambos
     */
    public UsuarioCuenta agregarUsuarioCuenta(Cuenta cuenta, Usuario usuario){
        UsuarioCuenta usuarioCuenta = new UsuarioCuenta();
        usuarioCuenta.setUsuario(usuario);
        usuarioCuenta.setCuenta(cuenta);
        return usuarioCuentaRepository.save(usuarioCuenta);
    }
    @Transactional
    public UsuarioCuenta agregarUsuarioCuenta(Cuenta cuenta, Usuario usuario, boolean esAdmin) {
        System.out.println("Cuenta: " + cuenta.getId());

        usuario = usuarioService.buscarUsuarioId(usuario.getId());

        UsuarioCuenta usuarioCuenta = new UsuarioCuenta();
        usuarioCuenta.setUsuario(usuario);
        usuarioCuenta.setCuenta(cuenta);
        usuarioCuenta.setAdmin(esAdmin);
        return usuarioCuentaRepository.save(usuarioCuenta);
    }


    /**
     * Elimina un usuario de una cuenta
     * @param cuenta La cuenta a la que lo queremos añadir
     * @param usuario El usuario que queremos añadir
     * @return un boolean que indica si a sido eliminado o no
     */
    public boolean eliminarUsuarioCuenta(Cuenta cuenta, Usuario usuario){
        UsuarioCuenta usuarioCuenta = usuarioCuentaRepository.findTopByUsuarioEqualsAndCuentaEquals(usuario, cuenta).orElse(null);

        if (usuarioCuenta != null){
            usuarioCuentaRepository.delete(usuarioCuenta);
            return true;
        }
        return false;
    }

    /**
     * Listar los usuarios que hay en una cuenta
     * @param cuenta La cuenta que queremos para filtrar
     * @return una lista de usuarios a la que pertenecen a la cuenta
     */
    public List<Usuario> listaUsuarios (Cuenta cuenta){
        List<UsuarioCuenta> usuarioCuentas = usuarioCuentaRepository.findAllByCuentaEquals(cuenta);
        List<Usuario> usuarios = new ArrayList<>();
        for (UsuarioCuenta c : usuarioCuentas){
            usuarios.add(c.getUsuario());
        }

        return usuarios;
    }

    /**
     * Listar las cuentas que tiene un usuario
     * @param usuario El usuario que queremos para filtrar
     * @return una lista de cuentas a las que pertenece el usuario
     */
    public List<Cuenta> listaCuentas (Usuario usuario) {

        return usuarioCuentaRepository.listarCuentasUsuario(usuario);
    }

}
