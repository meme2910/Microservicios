package com.fyg.trabajador.service;

import org.springframework.http.ResponseEntity;

import com.fyg.trabajador.entity.Trabajador;

public interface TrabajadorService {
    
    ResponseEntity<?> getById(Long id) throws Exception;
    ResponseEntity<?> getNombreById(Long id) throws Exception;
    ResponseEntity<?> getAll() throws Exception;

    ResponseEntity<?> insert(Trabajador request) throws Exception;

    ResponseEntity<?> update(Trabajador request) throws Exception;

    ResponseEntity<?> delete(Long id) throws Exception;

}
