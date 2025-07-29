package com.fyg.nomina.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fyg.nomina.service.FileService;


@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping(value="/consultaArchivos", method=RequestMethod.GET)
    public ResponseEntity<?> consultaArchivos() {
        try {
            return fileService.consultaArchivos();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    
}
