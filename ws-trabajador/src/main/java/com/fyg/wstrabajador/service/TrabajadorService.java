package com.fyg.wstrabajador.service;

import org.springframework.http.ResponseEntity;

import com.fyg.wstrabajador.entity.Trabajador;

public interface TrabajadorService {
    
    ResponseEntity<?> insert(Trabajador request) throws Exception;

}
