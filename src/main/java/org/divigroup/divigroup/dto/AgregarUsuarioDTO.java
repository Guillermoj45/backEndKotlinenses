package org.divigroup.divigroup.dto;

import lombok.Data;

@Data
public class AgregarUsuarioDTO {
    private String username;
    private String email;
    private String avatar;
    private String tipoPago;
    private String password;

}
