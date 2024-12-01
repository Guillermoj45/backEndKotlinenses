package org.divigroup.divigroup.controller;


import lombok.AllArgsConstructor;
import org.divigroup.divigroup.dto.*;
import org.divigroup.divigroup.model.Cuenta;
import org.divigroup.divigroup.model.Producto;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.service.CuentaService;
import org.divigroup.divigroup.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/grupo")
@AllArgsConstructor
public class GrupoController {

    private final CuentaService cuentaService;
    private final ProductoService productoService;

    @PostMapping(value = "nuevo/{idUsuario}", consumes = "application/json", produces = "application/json")
    public CuentaDataDTO crearCuenta(@PathVariable int idUsuario, @RequestBody AgregarCuentaDTO cuenta){
        return cuentaService.crearCuenta(cuenta, idUsuario);
    }

    @GetMapping(value = "cuenta/{idCuenta}", produces = "application/json")
    public CuentaDataDTO encontrarCuenta(@PathVariable int idCuenta) throws Exception {
        return cuentaService.encontrarCuenta(idCuenta);
    }

    @PostMapping("/participantes/nuevo")
    public GrupoListaParticipantesDTO agregarUsuarioCuenta(@RequestBody GrupoParticipanteDTO dto){
        return cuentaService.agregarUsuarioCuenta(dto);
    }

    @GetMapping("/participantes/{idCuenta}")
    public GrupoListaParticipantesDTO listaParticipantes(@PathVariable int idCuenta){
        return cuentaService.listaParticipantes(idCuenta);
    }

    @DeleteMapping("/participantes/eliminar")
    public GrupoListaParticipantesDTO eliminarUsuarioCuenta(@RequestBody GrupoParticipanteDTO dto){
        return cuentaService.eliminarUsuarioCuenta(dto);
    }

    @GetMapping("/{idUsuario}")
    public List<CuentaDataDTO> listarCuentas(@PathVariable int idUsuario) {
        return cuentaService.listarCuentas(idUsuario);
    }

    // @PostMapping(value = "gasto/nuevo/{idUsuario}", consumes = "multipart/form-data", produces = "application/json")
    // public SoloProductoDTO agregarGasto(
    //         @PathVariable int idUsuario,
    //         @RequestPart("dto") AgregarGastoDTO dto,
    //         @RequestPart(value = "imagen", required = false) MultipartFile imagen,
    //         @RequestPart(value = "factura", required = false) MultipartFile factura) {
    //     dto.setIdUsuario(idUsuario);
    //     return cuentaService.agregarGasto(dto, imagen, factura);
    // }

    @PostMapping(value = "gasto/nuevo/{idUsuario}", produces = "application/json")
    public SoloProductoDTO agregarGasto(@PathVariable int idUsuario, @RequestBody AgregarGastoDTO dto) {
        dto.setIdUsuario(idUsuario);

        return cuentaService.agregarGasto(dto, null, null);
    }

    @GetMapping("gasto/{idCuenta}")
    public List<SoloProductoDTO> listarGastos(@PathVariable int idCuenta){
        return productoService.encontrarPorCuenta(idCuenta);
    }

//    @GetMapping("gasto")
//    public List<SoloProductoDTO> listarGastos(@CookieValue(value = "idUsuario", defaultValue = "defaultCookieValue")int idUsuarioCookie){
//        System.out.println("Cookie: " + idUsuarioCookie);
//        return productoService.encontrarPorCuenta(idUsuarioCookie);
//    }

    @GetMapping("gastos/{idGrupo}")
    public HashMap<String, Float> listarGastosGrupo(@PathVariable int idGrupo){
        return productoService.puestaEnCuentas(idGrupo);
    }
}
