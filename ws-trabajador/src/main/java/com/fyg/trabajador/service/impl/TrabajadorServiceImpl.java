package com.fyg.trabajador.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fyg.trabajador.entity.Trabajador;
import com.fyg.trabajador.repository.TrabajadorRepository;
import com.fyg.trabajador.service.TrabajadorService;

@Service("trabajdorService")
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public ResponseEntity<?> getById(Long id) throws Exception {
        try {
            if(id==null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id es requerido, favor de verificar!");
            }
            Optional<Trabajador> trabajador = trabajadorRepository.findById(id);
            if (trabajador.isPresent()) {
                return ResponseEntity.ok(trabajador.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado con el id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar su solicitud:" + e.getMessage());
        }
    }
    @Override
    public ResponseEntity<?> getNombreById(Long id) throws Exception {
        try {
            if(id==null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id es requerido, favor de verificar!");
            }
            Optional<Trabajador> trabajador = trabajadorRepository.findById(id);
            if (trabajador.isPresent()) {
                return ResponseEntity.ok(trabajador.get().getNombreCompleto());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado con el id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar su solicitud:" + e.getMessage());
        }
    }
    @Override
    public ResponseEntity<?> getAll() throws Exception {
        try {
            List<Trabajador> trabajadores = trabajadorRepository.findAll();
            if (trabajadores == null || trabajadores.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron trabajadores.");
            }else {

                return ResponseEntity.ok(trabajadores);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar su solicitud:" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> insert(Trabajador request) throws Exception {
        if(request.getId()!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id no es requerido, favor de verificar!!");
        }
        if (request.getNombre()==null || request.getNombre().isEmpty()
        || request.getAMaterno()==null || request.getAMaterno().isEmpty()
        || request.getAPaterno()==null || request.getAPaterno().isEmpty()
        || request.getPuesto()==null || request.getPuesto().isEmpty()
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos Nombre, A_Paterno, A_Materno y Puesto son requeridos");
        }
        try {
            Trabajador save = trabajadorRepository.save(request);
            return ResponseEntity.ok("Su solicitud se proceso con Exito!!, Trabajador:" + save.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar su solicitud:" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> update(Trabajador request) throws Exception {
        if(request.getId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id es requerido, favor de verificar!!");
        }
        if (request.getNombre()==null || request.getNombre().isEmpty()
        || request.getAMaterno()==null || request.getAMaterno().isEmpty()
        || request.getAPaterno()==null || request.getAPaterno().isEmpty()
        || request.getPuesto()==null || request.getPuesto().isEmpty()
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos Nombre, A_Paterno, A_Materno y Puesto son requeridos");
        }
        try {
            Trabajador save = trabajadorRepository.save(request);
            return ResponseEntity.ok("Su solicitud se proceso con Exito!!, Trabajador:" + save.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar su solicitud:" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) throws Exception {
        try {
            if(id==null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id es requerido, favor de verificar!");
            }
            if(trabajadorRepository.existsById(id)){
                Optional<Trabajador> ot = trabajadorRepository.findById(id);
                trabajadorRepository.deleteById(id);
                return ResponseEntity.ok("Registro Eliminado Exitosamente!!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado con el id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar su solicitud:" + e.getMessage());
        }
    }
    
   

}
