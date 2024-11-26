package org.divigroup.divigroup.service;

import lombok.NoArgsConstructor;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class UsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    public Usuario buscarUsuarioId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Integer buscarUsuarioUserName(String userName) {
        Integer idUsuario = usuarioRepository.buscarNombre(userName).orElse(null);

        if (idUsuario == null) {
            idUsuario = -1;
        }

        return idUsuario;
    }

    public boolean autentificarUsuarioenlabdd(String username, String password) {
        List<Usuario> user = usuarioRepository.buscarUsuarios(username, password);
        if (!user.isEmpty() && user.get(0).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public Usuario registrarUsuario(Usuario newUsuario) {
        return usuarioRepository.save(newUsuario);
    }
}