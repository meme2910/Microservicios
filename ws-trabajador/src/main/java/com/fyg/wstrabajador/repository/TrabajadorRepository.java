package com.fyg.wstrabajador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fyg.wstrabajador.entity.Trabajador;


public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

    @Query(value = "SELECT nombre FROM td_trabajador WHERE id= ?1", nativeQuery= true)
    public String getNombreById(Long id);

}
