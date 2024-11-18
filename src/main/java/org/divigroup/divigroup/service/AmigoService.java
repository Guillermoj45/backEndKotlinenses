package org.divigroup.divigroup.service;

import org.divigroup.divigroup.model.Amigo;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.repository.IAmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmigoService {
    @Autowired
    IAmigoRepository amigoRepository;
    @Autowired
    UsuarioService usuarioService;

    /**
     * Lista todos los amigos de un usuario
     * @param usuario El usuario sobre el que queremos hacer las consultas
     * @return Lista de amigos
     */
    public List<Usuario> amigosUsuario (int idUsuario){
        Usuario usuario = usuarioService.buscarUsuarioId(idUsuario);

        List<Amigo> amigos = amigoRepository.amigos(usuario);
        List<Usuario> participantes = new ArrayList<>();

        for (Amigo a : amigos){
            if (a.getAmigo().equals(usuario)){
                participantes.add(a.getUser());
            }else {
                participantes.add(a.getAmigo());
            }
        }
        return participantes;
    }
}
