package org.divigroup.divigroup.service;

import lombok.NoArgsConstructor;
import org.divigroup.divigroup.dto.IdUsuarioDTO;
import org.divigroup.divigroup.dto.UsuarioDTO;
import org.divigroup.divigroup.model.Usuario;
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
        Integer idUsuario = usuarioRepository.buscarNombre(userName).orElse(null);

        if (idUsuario == null)
        {
            idUsuario = -1;
        }

        return idUsuario;
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
}
