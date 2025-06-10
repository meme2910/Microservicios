package com.fyg.nomina.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReporteAsistenciaResponse {
    
    private Long id;
    private Trabajador trabajador;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha;
    private String horaEnrtada;
    private String horaSalida;

    @Data
    public static class Trabajador{
        private Long id;
        private String nombre;
        private String apaterno;
        private String amaterno;
        private String puesto;
    }

}
