package com.fyg.wstrabajador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fyg.wstrabajador.entity.Checador;

public interface ChecadorRepository extends JpaRepository<Checador, Long> {

    @Query(value="SELECT * FROM tc_checador WHERE Id_Trabajador = ?1 and CAST(Fecha AS DATE) = ?2",nativeQuery=true)
    List<Checador> findByIdTrabajadorFecha(Long idTrabajador, String fecha);
    
    @Query(value="SELECT * FROM tc_checador WHERE CAST(fecha AS DATE) between ?1 AND ?2",nativeQuery = true)
    List<Checador> findByFechaBetween(String fechaInicio, String fechaFin);
}
