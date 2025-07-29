package com.fyg.nomina.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fyg.nomina.service.FileService;


@Service("fileService")
public class FileServiceImpl implements FileService{

    @Autowired
    private Environment env;

    @Override
    public ResponseEntity<?> consultaArchivos() throws Exception {
        try {
            String ruta = env.getProperty("openshift.volumen.persistente");
            File directorio = new File(ruta);
            if (!directorio.exists() || !directorio.isDirectory()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La ruta especificada no es un directorio válido: " + ruta);
            }
            File[] archivos = directorio.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
            List<String> nombresArchivos = new ArrayList<>();
            if (archivos != null) {
                for (File archivo : archivos) {
                    nombresArchivos.add(archivo.getName());
                }
            }
            return ResponseEntity.ok(nombresArchivos.isEmpty()?"No se encontraron archivos!!":nombresArchivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar su solicitud:" + e.getMessage());
        }
    }
    
}
