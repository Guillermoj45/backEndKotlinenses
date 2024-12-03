package org.divigroup.divigroup.controller;

import org.divigroup.divigroup.dto.IdUsuarioDTO;
import org.divigroup.divigroup.dto.UsuarioDTO;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login/{username}/{password}")
    public Integer login(@PathVariable String username, @PathVariable String password) {
        return usuarioService.buscarUsuarioUserName(username);
    }

    @GetMapping("/id/{userId}")
    public Usuario buscarId(@PathVariable Integer userId) {
        return usuarioService.buscarUsuarioId(userId);
    }

    @PostMapping("/id")
    public Usuario guardar(@RequestBody UsuarioDTO user) {
        return usuarioService.guardarUsuario(user);
    }
}