package org.divigroup.divigroup.service;

import lombok.NoArgsConstructor;
import org.divigroup.divigroup.dto.AgregarUsuarioDTO;
import org.divigroup.divigroup.dto.IdUsuarioDTO;
import org.divigroup.divigroup.dto.UsuarioDTO;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.model.enums.Rol;
import org.divigroup.divigroup.model.enums.TipoPago;
import org.divigroup.divigroup.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    /**
     * Buscar un usuario por id
     * @param id Id del usuario
     * @return Usuario
     */
    public Usuario buscarUsuarioId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Buscar un usuario por nombre
     * @param userName userName del usuario
     * @return Usuario
     */
    public Integer buscarUsuarioUserName(String userName) {
        return usuarioRepository.buscarNombre(userName).orElse(-1);
    }

    /**
     * Guardar un usuario
     * @param user Usuario a guardar
     * @return Usuario
     */
    public Usuario guardarUsuario(UsuarioDTO user) {
        Usuario usuario = usuarioRepository.findById(user.getId()).orElse(null);

        if (usuario != null)
        {
            usuario.setUsername(user.getUsername());
            usuario.setEmail(user.getEmail());
            usuario.setAvatar(user.getAvatar());
            usuario.setTipoPago(TipoPago.valueOf(user.getTipoPago()));
            return usuarioRepository.save(usuario);
        }

        return null;
    }
public Usuario registrarUsuario(AgregarUsuarioDTO user) {
    Usuario usuario = new Usuario();
    usuario.setUsername(user.getUsername());
    usuario.setEmail(user.getEmail());
    usuario.setAvatar(user.getAvatar());
    usuario.setTipoPago(TipoPago.valueOf(user.getTipoPago()));
    usuario.setRol(Rol.USER);
    usuario.setPassword(user.getPassword()); // Ensure password is set
    return usuarioRepository.save(usuario);
}

}
