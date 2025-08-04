package com.fyg.trabajador.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fyg.trabajador.entity.Trabajador;
import com.fyg.trabajador.service.TrabajadorService;

@RestController
@RequestMapping("/trabajador")
public class TrabajadorController {
    private static final Logger LOG = LoggerFactory.getLogger(TrabajadorController.class);

    @Autowired
    private TrabajadorService service;


    @RequestMapping(value="/getById/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        LOG.info("Request: "+ id.toString());
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e){
            LOG.info("Error al procesar su solicitud: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    @RequestMapping(value="/getNombreById/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getNombreById(@PathVariable Long id) {
        LOG.info("Request: "+ id.toString());
        try {
            return ResponseEntity.ok(service.getNombreById(id));
        } catch (Exception e){
            LOG.info("Error al procesar su solicitud: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    @RequestMapping(value="/getAll", method=RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(service.getAll());
        } catch (Exception e){
            LOG.info("Error al procesar su solicitud: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    
    @PostMapping(value="/insert")
    public ResponseEntity<?> insert(@RequestBody Trabajador trabajador) {
        LOG.info("Request: "+ trabajador.toString());
        try {
            return service.insert(trabajador);
        } catch (Exception e) {
            LOG.info("Error al procesar su solicitud: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al procesar su solicitud: " + e.getMessage());
        }
    }

    @RequestMapping(value="/update", method=RequestMethod.PATCH)
    public ResponseEntity<?> update(@RequestBody Trabajador trabajador) {
        LOG.info("Request: "+ trabajador.toString());
        try {
            return service.update(trabajador);
        } catch (Exception e) {
            LOG.info("Error al procesar su solicitud: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        LOG.info("Request: "+ id.toString());
        try {
            return service.delete(id);
        } catch (Exception e) {
            LOG.info("Error al procesar su solicitud: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al procesar su solicitud: " + e.getMessage());
        }
        
    }
    
}
