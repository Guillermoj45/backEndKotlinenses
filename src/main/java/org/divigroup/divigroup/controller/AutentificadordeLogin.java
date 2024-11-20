package org.divigroup.divigroup.controller;

import org.divigroup.divigroup.dto.LoginDTO;
import org.divigroup.divigroup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AutentificadordeLogin {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        boolean isAuthenticated = usuarioService.autentificarUsuarioenlabdd(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Acceso concedido");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
        }
    }



}
