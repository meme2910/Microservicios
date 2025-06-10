package com.fyg.wstrabajador.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tc_checador")
public class Checador {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="Id_trabajador", referencedColumnName="id")
    private Trabajador trabajador;
    @Column(name="fecha")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha;
    @Column(name="Hora_Entrada")
    private String horaEntrada;
    @Column(name="Hora_Salida")
    private String horaSalida;

}
