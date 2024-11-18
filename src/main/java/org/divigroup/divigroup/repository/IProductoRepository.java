package org.divigroup.divigroup.repository;

import org.divigroup.divigroup.model.Cuenta;
import org.divigroup.divigroup.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("select p " +
            "from Producto p " +
            "where p.cuenta = :cuenta")
    public List<Producto> encontrarPorCuenta(Cuenta cuenta);

}
