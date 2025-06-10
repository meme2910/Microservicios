package com.fyg.wstrabajador.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="td_trabajador")
public class Trabajador {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido_paterno")
    private String aPaterno;
    @Column(name="apellido_materno")
    private String aMaterno;
    @Column(name="puesto")
    private String puesto;
    
}
