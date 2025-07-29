package com.fyg.trabajador.service;

import org.springframework.http.ResponseEntity;

public interface FileService {
    
    ResponseEntity<?> generaArchivo() throws Exception;

}
