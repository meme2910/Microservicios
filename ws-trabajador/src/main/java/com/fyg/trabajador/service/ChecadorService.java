package com.fyg.trabajador.service;

import org.springframework.http.ResponseEntity;

import com.fyg.trabajador.model.request.ReporteAsistenciaRequest;

public interface  ChecadorService {
    
    public ResponseEntity<?> checarAsistencia(Long request) throws Exception;

    public ResponseEntity<?> reporteAsistencia(ReporteAsistenciaRequest request) throws Exception;

}
