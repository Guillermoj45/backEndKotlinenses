package org.divigroup.divigroup.controller;

import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.service.AmigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/amigos")
public class AmigoControler {
    @Autowired
    private AmigoService amigoService;

    @GetMapping("{idUsuario}")
    public List<Usuario> listarAmigos(@PathVariable int idUsuario){
        return amigoService.amigosUsuario(idUsuario);
    }
}
