package com.fyg.trabajador.service.impl;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fyg.trabajador.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService{
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private Environment env;

    @Override
    public ResponseEntity<?> generaArchivo() throws Exception {
        String ruta = env.getProperty("openshift.volumen.persistente");
        LOG.info("RUTA->"+ruta);
        String nombreArchivo = System.currentTimeMillis() + ".csv";
        LOG.info("NOMBRE_ARCHIVO->"+nombreArchivo);
        String archivoCsv = ruta.endsWith("/") ? ruta + nombreArchivo : ruta + "/" + nombreArchivo;
        LOG.info("ARCHIVO_CSV->"+archivoCsv);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        LOG.info("NOW->"+now);
        String pod = env.getProperty("openshift.pod");
        LOG.info("POD->"+pod);
        try {
            PrintWriter writter = new PrintWriter(new FileWriter(archivoCsv));
            writter.println("Fecha,POD");
            writter.println(now+","+pod);
            writter.close();
            return ResponseEntity.ok("El archivo se genero de manera correcta");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar su solicitud:" + e.getMessage());
        }
    }
    
}
