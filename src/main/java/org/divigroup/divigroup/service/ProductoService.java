package org.divigroup.divigroup.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.divigroup.divigroup.dto.SoloProductoDTO;
import org.divigroup.divigroup.model.Cuenta;
import org.divigroup.divigroup.model.HistorialPago;
import org.divigroup.divigroup.model.Producto;
import org.divigroup.divigroup.model.Usuario;
import org.divigroup.divigroup.repository.IProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Clase que se encarga de la logica de negocio de los productos
 */
@Service
@AllArgsConstructor
public class ProductoService {
    private IProductoRepository productoRepository;

    private CuentaService cuentaService;

    private HistorialPagoService historialPagoService;

    private Cloudinary cloudinary;

    /**
     * Método que se encarga de crear un producto
     * @param producto producto a crear
     * @return producto creado
     */
    public Producto crearProducto(Producto producto, MultipartFile imagenURL, MultipartFile faturaURL) {
        try {
            if (imagenURL != null) {
                File imagen = new File(System.getProperty("java.io.tmpdir") + "/" + imagenURL.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(imagen);
                fos.write(imagenURL.getBytes());
                fos.close();

                var pic = cloudinary.uploader().upload(imagen, ObjectUtils.asMap("folder", "divigroup"));

                producto.setImagen(pic.get("url").toString());

            }

            if (faturaURL != null) {
                File factura = new File(System.getProperty("java.io.tmpdir") + "/" + faturaURL.getOriginalFilename());
                FileOutputStream fos2 = new FileOutputStream(factura);
                fos2.write(faturaURL.getBytes());
                fos2.close();

                var pic2 = cloudinary.uploader().upload(factura, ObjectUtils.asMap("folder","divigroup"));

                producto.setFactura(pic2.get("url").toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return productoRepository.save(producto);
    }

    /**
     * Método que se encarga de encontrar un producto por su id
     * @param idCuenta id del producto
     * @return producto encontrado
     */
    public List<SoloProductoDTO> encontrarPorCuenta(int idCuenta) {
        Cuenta cuenta = cuentaService.buscarCuentaId(idCuenta);
        List<Producto> listaProducto = productoRepository.encontrarPorCuenta(cuenta);
        return SoloProductoDTO.pasarALista(listaProducto);
    }

    /**
     * Método que se encarga de encontrar un producto por su id
     * @param idGrupo id del producto
     * @return producto encontrado
     */
    public HashMap<String, Float> puestaEnCuentas (int idGrupo) {
        Cuenta cuenta = cuentaService.buscarCuentaId(idGrupo);
        Set<Usuario> participantes = new HashSet<>(cuentaService.listaParticipantes(idGrupo).getParticipantes());

        HashMap<String, List<SoloProductoDTO>> gastos = new HashMap<>();

        for (Usuario u : participantes){
            gastos.put(u.getUsername(), new ArrayList<>());
        }

        List<Producto> listaProducto = productoRepository.encontrarPorCuenta(cuenta);
        for (Producto p : listaProducto){
            SoloProductoDTO dto = new SoloProductoDTO(p);
            gastos.get(p.getUser().getUsername()).add(dto);
        }
        HashMap<String, Float> suma = new HashMap<>();

        for (String u : gastos.keySet()){
            Float total = 0.0F;
            for (SoloProductoDTO p : gastos.get(u)){
                total += p.getPrecio();
            }
            suma.put(u, total);
        }

        Float total = 0.0F;
        for (String u : suma.keySet()){
            System.out.println(u + ": " + suma.get(u));
            total += suma.get(u);
            System.out.println("Total: " + total);
        }
        total = Float.parseFloat(String.format("%.2f", total).replace(",", "."));
        System.out.println("Total: " + total);

        float totalPorPersona = total / participantes.size();
        suma.forEach((k, v) -> {
            suma.put(k, Float.parseFloat(String.format("%.2f", v - totalPorPersona).replace(",", ".")));
        });

        List<HistorialPago> historial = historialPagoService.encontrarPorCuenta(cuenta);
        Float totalHistorial = 0.0F;
        for (HistorialPago h : historial){
            String nombreUsuario = h.getUsuario().getUsername();
            totalHistorial += h.getMonton();
            suma.put(nombreUsuario,Float.parseFloat(String.format("%.2f", suma.get(nombreUsuario) + h.getMonton()).replace(",", ".")));
        }

        for (String u : suma.keySet()){
            if (suma.get(u) > 0){
                float resta = suma.get(u) - totalHistorial;
                resta = Float.parseFloat(String.format("%.2f", resta).replace(",", "."));

                if (resta < 0){
                    totalHistorial = Math.abs(resta);
                    suma.put(u, 0.0F);
                } else {
                    suma.put(u, resta);

                }
            }
        }

        return suma;
    }
}
