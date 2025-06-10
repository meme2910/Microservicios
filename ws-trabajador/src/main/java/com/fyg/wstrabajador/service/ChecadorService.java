package com.fyg.wstrabajador.service;

import org.springframework.http.ResponseEntity;

import com.fyg.wstrabajador.model.request.ReporteAsistenciaRequest;

public interface  ChecadorService {
    
    public ResponseEntity<?> checarAsistencia(Long request) throws Exception;

    public ResponseEntity<?> reporteAsistencia(ReporteAsistenciaRequest request) throws Exception;

}
