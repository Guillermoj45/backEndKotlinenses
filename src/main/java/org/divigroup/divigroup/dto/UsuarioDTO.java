package org.divigroup.divigroup.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    Integer id;
    String username;
    String email;
    String avatar;
    String tipoPago;
    String rol;
    String password;


}
