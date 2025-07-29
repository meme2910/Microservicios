package com.fyg.trabajador.service;

import org.springframework.http.ResponseEntity;

import com.fyg.trabajador.entity.Trabajador;

public interface TrabajadorService {
    
    ResponseEntity<?> insert(Trabajador request) throws Exception;

}
