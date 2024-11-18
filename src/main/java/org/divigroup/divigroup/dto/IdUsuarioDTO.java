package org.divigroup.divigroup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdUsuarioDTO {
    private int idUsuario;

    public IdUsuarioDTO(int idUsuario) {
        this.setIdUsuario(idUsuario);
    }
}
