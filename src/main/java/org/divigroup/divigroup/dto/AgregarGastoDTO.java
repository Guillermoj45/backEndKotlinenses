package org.divigroup.divigroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.divigroup.divigroup.model.Producto;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgregarGastoDTO {
    private int idUsuario;
    private int idGrupo;
    private AgregarProducto producto;

}
