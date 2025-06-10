package com.fyg.wstrabajador.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fyg.wstrabajador.model.request.ReporteAsistenciaRequest;
import com.fyg.wstrabajador.service.ChecadorService;

@RestController
@RequestMapping("/checador")
public class ChecadorController {
    private static final Logger LOG = LoggerFactory.getLogger(ChecadorController.class);
    
    @Autowired
    private ChecadorService checadorService;

    @RequestMapping(value="/checarAsistencia", method=RequestMethod.POST)
    public ResponseEntity<?> requestMethodName(@RequestBody Long request) {
        try {
            return checadorService.checarAsistencia(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar su solicitud: " + e.getMessage());
        }
    }

    @RequestMapping(value="/reporteAsistencia", method=RequestMethod.POST)
    public ResponseEntity<?> requestMethodName(@RequestBody ReporteAsistenciaRequest request) {
         try {
            LOG.info("REQUEST: --->" + request);
            return checadorService.reporteAsistencia(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    
    

}
