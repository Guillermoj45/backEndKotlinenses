package org.divigroup.divigroup.controller;

import org.divigroup.divigroup.dto.IdUsuarioDTO;
import org.divigroup.divigroup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login/{username}/{password}")
    public Integer login(@PathVariable String username, @PathVariable String password) {
        return usuarioService.buscarUsuarioUserName(username);
    }
}