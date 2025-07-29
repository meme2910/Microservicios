package com.fyg.nomina.service;

import org.springframework.http.ResponseEntity;

public interface FileService {
    
    ResponseEntity<?> consultaArchivos() throws Exception;

}
