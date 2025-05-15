package com.hlca.hlcamatriculaservicio.Feign;

import com.hlca.hlcamatriculaservicio.Dto.EstudianteDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="hlca-estudiante-servicio", path = "/estudiantes")
public interface EstudianteFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "estudianteListarPorIdCB", fallbackMethod = "fallbackEstudianteListarPorId")
    public ResponseEntity<EstudianteDto> buscarEstudiante(@PathVariable Long id);

    default ResponseEntity<EstudianteDto> fallbackEstudianteListarPorId(Long id, Exception e) {
        EstudianteDto estudianteDto = new EstudianteDto();
        estudianteDto.setNombre("Servicio no disponible de estudiantes");
        return ResponseEntity.ok(estudianteDto);
    }
}
