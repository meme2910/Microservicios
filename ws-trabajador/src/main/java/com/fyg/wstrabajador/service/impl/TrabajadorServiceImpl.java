package com.fyg.wstrabajador.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fyg.wstrabajador.entity.Trabajador;
import com.fyg.wstrabajador.repository.TrabajadorRepository;
import com.fyg.wstrabajador.service.TrabajadorService;

@Service("trabajdorService")
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public ResponseEntity<?> insert(Trabajador request) throws Exception {
        //if(request.getId()!=null){
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id no es requerido, favor de verificar!!");
        //}
        if (request.getNombre()==null || request.getNombre().isEmpty()
        || request.getAMaterno()==null || request.getAMaterno().isEmpty()
        || request.getAPaterno()==null || request.getAPaterno().isEmpty()
        || request.getPuesto()==null || request.getPuesto().isEmpty()
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos Nombre, A_Paterno, A_Materno y Puesto son requeridos");
        }
        try {
            trabajadorRepository.save(request);
            return ResponseEntity.ok("Su solicitud se proceso con Exito!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar su solicitud:" + e.getMessage());
        }
    }
    
   

}
