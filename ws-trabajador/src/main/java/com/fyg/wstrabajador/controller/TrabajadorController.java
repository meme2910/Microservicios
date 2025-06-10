package com.fyg.wstrabajador.controller;


import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fyg.wstrabajador.entity.Trabajador;
import com.fyg.wstrabajador.repository.TrabajadorRepository;
import com.fyg.wstrabajador.service.TrabajadorService;

@RestController
@RequestMapping("/trabajador")
public class TrabajadorController {
    private static final Logger LOG = Logger.getLogger(TrabajadorController.class.getName());

    @Autowired
    private TrabajadorRepository repository;
    @Autowired
    private TrabajadorService service;


    @GetMapping("/getHolaMundo")
    public String getHolaMundo() {
        return "Hola Alumnos FYG";
    }
    
    @RequestMapping(value="/getId/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> requestMethodName(@PathVariable Long id) {
        LOG.info("PathVariable: " + id);
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping(value="/insert")
    public ResponseEntity<?> insert(@RequestBody Trabajador trabajador) {
        try {
            return service.insert(trabajador);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar su solicitud: " + e.getMessage());
        }
    }

    @RequestMapping(value="/update", method=RequestMethod.PATCH)
    public ResponseEntity<?> updte(@RequestBody Trabajador trabajador) {
        LOG.info("JSON Recibido: " + trabajador);
        if(trabajador==null || trabajador.getId()==null) return ResponseEntity.badRequest().body("El id es requerido");
        if(repository.existsById(trabajador.getId())){
            repository.save(trabajador);
            return ResponseEntity.ok("Registro modificado con exito!!");
        }else{
            LOG.info("HUBO UN PROBLEMA :(");
            return ResponseEntity.badRequest().body("Errror al procesar su solicitud!!");
        }
    }
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        LOG.info("PathVariable: " + id);
        if(repository.existsById(id)){
            Optional<Trabajador> ot = repository.findById(id);
            repository.deleteById(id);
            LOG.info("TODO OK!!");
            return ResponseEntity.ok("Ok");
        }else{
            LOG.info("HUBO UN PROBLEMA :(");
            return ResponseEntity.badRequest().body("Errror al procesar su solicitud!!");
        }
        
    }
    
    
    @RequestMapping(value="/getNombreById/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getNombreById(@PathVariable Long id) {
        LOG.info("PathVariable: " + id);
        return ResponseEntity.ok(repository.getNombreById(id));
    }
}
