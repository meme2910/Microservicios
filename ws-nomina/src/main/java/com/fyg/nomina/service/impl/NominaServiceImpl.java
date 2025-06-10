package com.fyg.nomina.service.impl;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fyg.nomina.model.request.ReporteAsistenciaRequest;
import com.fyg.nomina.model.response.ReporteAsistenciaResponse;
import com.fyg.nomina.service.NominaService;

import jakarta.annotation.PostConstruct;

@Service("nominaService")
public class NominaServiceImpl implements NominaService {

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Value("${ws.trabajador.host}")
    private String host;
    @Value("${ws.trabajador.path}")
    private String path;
    @Autowired
    private Environment env;

    public NominaServiceImpl(WebClient.Builder webClientBuilder){
        this.webClientBuilder=webClientBuilder;
    }

    @PostConstruct
    private void init(){
        this.webClient = webClientBuilder
            .baseUrl(host)
            .build();
    }

    @Override
    public ResponseEntity<?> reporteAsistencia(ReporteAsistenciaRequest request) throws Exception {
         if(request==null || request.getFechaInicio()==null || request.getFechaFin()==null){
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
        }
        if(request.getFechaInicio().after(request.getFechaFin())){
            return ResponseEntity.badRequest().body("La fecha de inicio no puede ser mayot a la fecha fin");
        }
        try{
            String endPoint = path + env.getProperty("ws.trabajador.checador.reporteAsistencia");
            List<ReporteAsistenciaResponse> reporte = webClient.post()
                .uri(endPoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ReporteAsistenciaResponse>>(){})
                .timeout(Duration.ofSeconds(2))
                .block();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
}
