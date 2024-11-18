package org.divigroup.divigroup.controller;


import lombok.AllArgsConstructor;
import org.divigroup.divigroup.dto.*;
import org.divigroup.divigroup.model.Cuenta;
import org.divigroup.divigroup.model.Producto;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.service.CuentaService;
import org.divigroup.divigroup.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/grupo")
@AllArgsConstructor
public class GrupoController {

    private final CuentaService cuentaService;
    private final ProductoService productoService;

    @PostMapping("/nuevo")
    public Cuenta crearCuenta(@CookieValue(value = "idUsuario") int idUsuario ,@RequestBody AgregarCuentaDTO cuenta){
        return cuentaService.crearCuenta(cuenta, idUsuario);
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

    @GetMapping()
    public List<Cuenta> listarCuentas(@CookieValue(value = "idUsuario") int idUsuario) {
        return cuentaService.listarCuentas(idUsuario);
    }

    @PostMapping("gasto/nuevo")
    public SoloProductoDTO agregarGasto(@CookieValue(value = "idUsuario") int idUsuario, @RequestBody AgregarGastoDTO dto){
        dto.setIdUsuario(idUsuario);
        return cuentaService.agregarGasto(dto);
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
