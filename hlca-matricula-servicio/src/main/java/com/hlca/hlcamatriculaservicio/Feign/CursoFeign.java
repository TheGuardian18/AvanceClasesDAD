package com.hlca.hlcamatriculaservicio.Feign;

import com.hlca.hlcamatriculaservicio.Dto.CursoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="hlca-curso-servicio", path = "/cursos")
public interface CursoFeign {
    @GetMapping("/{id}")
    //resiliencia
    @CircuitBreaker(name = "cursoListarPorIdCB", fallbackMethod = "fallbackCursoListarPorId")

    //tranceint
    public ResponseEntity<CursoDto> buscarCurso(@PathVariable Long id);

    //resiliencia
    default ResponseEntity<CursoDto> fallbackCursoListarPorId(Long id, Exception e) {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setNombre("Servicio no disponible de cursos");
        return ResponseEntity.ok(cursoDto);
    }
}
