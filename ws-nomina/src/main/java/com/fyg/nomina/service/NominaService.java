package com.fyg.nomina.service;

import org.springframework.http.ResponseEntity;

import com.fyg.nomina.model.request.ReporteAsistenciaRequest;

public interface NominaService {
    
    public ResponseEntity<?> reporteAsistencia(ReporteAsistenciaRequest request) throws Exception;  

}
