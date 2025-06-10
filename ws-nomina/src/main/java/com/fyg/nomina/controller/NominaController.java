package com.fyg.nomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fyg.nomina.model.request.ReporteAsistenciaRequest;
import com.fyg.nomina.service.NominaService;

@RestController
@RequestMapping("/nomina")
public class NominaController {

    @Autowired
    private NominaService nominaService;
    
    @RequestMapping(value="/reporteAsistencia", method=RequestMethod.POST)
    public ResponseEntity<?> requestMethodName(@RequestBody ReporteAsistenciaRequest request) {
         try {
            return nominaService.reporteAsistencia(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    
}
