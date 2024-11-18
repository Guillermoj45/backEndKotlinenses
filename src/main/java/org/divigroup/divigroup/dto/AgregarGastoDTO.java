package org.divigroup.divigroup.dto;

import lombok.Getter;
import lombok.Setter;
import org.divigroup.divigroup.model.Producto;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AgregarGastoDTO {
    private int idUsuario;
    private int idGrupo;
    private Producto producto;
    private MultipartFile factura;
    private MultipartFile imagen;
}
