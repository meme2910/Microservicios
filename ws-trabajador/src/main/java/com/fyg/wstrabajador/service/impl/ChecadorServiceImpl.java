package com.fyg.wstrabajador.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fyg.wstrabajador.entity.Checador;
import com.fyg.wstrabajador.entity.Trabajador;
import com.fyg.wstrabajador.model.request.ReporteAsistenciaRequest;
import com.fyg.wstrabajador.repository.ChecadorRepository;
import com.fyg.wstrabajador.repository.TrabajadorRepository;
import com.fyg.wstrabajador.service.ChecadorService;

@Service("checadorService")
public class ChecadorServiceImpl implements ChecadorService {

    @Autowired
    private ChecadorRepository checadorRepository;
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public ResponseEntity<?> checarAsistencia(Long request) throws Exception {
        if (request == null || request <=0 ) {
            return ResponseEntity.badRequest().body("El Id del trabajador no puede ser nulo o menor a cero.");
        }
        Optional<Trabajador> optional = trabajadorRepository.findById(request);
        if(optional==null || !optional.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El id no fue encontrado.");
        }
        Trabajador trabajador = optional.get();
        try{
            String hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<Checador> asistensia = checadorRepository.findByIdTrabajadorFecha(request, hoy);
            if (asistensia!=null && !asistensia.isEmpty()) {
                if (asistensia.get(0).getHoraSalida()!=null) {
                    return ResponseEntity.ok("El trabajador ya registro su asistencia el día de hoy");
                }else{
                    Checador checador = asistensia.get(0);
                    LocalTime horaActual = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    checador.setHoraSalida(horaActual.format(formatter));
                    checadorRepository.save(checador);
                    return ResponseEntity.ok("Horario de Salida Registrado para el empleado: " + request);
                }
            }
            Checador checador = new Checador();
            checador.setTrabajador(trabajador);
            checador.setFecha(new Date());
            LocalTime horaActual = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            checador.setHoraEntrada(horaActual.format(formatter));
            checador.setHoraSalida(null);
            checadorRepository.save(checador);
            return ResponseEntity.ok("Horario de Entrada Registrado para el empleado: " + request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar su solicitud: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> reporteAsistencia(ReporteAsistenciaRequest request) throws Exception{
        if(request==null || request.getFechaInicio()==null || request.getFechaFin()==null){
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
        }
        if(request.getFechaInicio().after(request.getFechaFin())){
            return ResponseEntity.badRequest().body("La fecha de inicio no puede ser mayot a la fecha fin");
        }
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String fechaInicio = formatter.format(request.getFechaInicio());
            String fechaFin = formatter.format(request.getFechaFin());
            List<Checador> asistensia = checadorRepository.findByFechaBetween(fechaInicio, fechaFin);
            if(asistensia==null || asistensia.isEmpty()){
                return ResponseEntity.ok("No se encontraron registros");
            }
            return ResponseEntity.ok(asistensia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
  
}
