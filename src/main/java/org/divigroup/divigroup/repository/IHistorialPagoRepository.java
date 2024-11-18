package org.divigroup.divigroup.repository;

import org.divigroup.divigroup.model.Cuenta;
import org.divigroup.divigroup.model.HistorialPago;
import org.divigroup.divigroup.service.HistorialPagoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IHistorialPagoRepository extends JpaRepository<HistorialPago,Integer> {
    @Query("""
            SELECT hp
            FROM HistorialPago hp
            WHERE hp.cuenta = :Cuenta
            """)
    List<HistorialPago> encontrarPorCuenta(Cuenta Cuenta);
}
