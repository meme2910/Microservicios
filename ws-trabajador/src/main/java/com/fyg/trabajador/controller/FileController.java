package com.fyg.trabajador.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fyg.trabajador.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping(value="/generaArchivo", method=RequestMethod.GET)
    public ResponseEntity<?> generaArchivo() {
        try {
            return fileService.generaArchivo();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    
}
