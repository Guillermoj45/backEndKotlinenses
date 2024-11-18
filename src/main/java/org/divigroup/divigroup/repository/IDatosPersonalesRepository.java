package org.divigroup.divigroup.repository;

import org.divigroup.divigroup.model.DatosPersonales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDatosPersonalesRepository extends JpaRepository<DatosPersonales, Integer> {
}
