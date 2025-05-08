package com.eja.prestamo.Feign;



import com.eja.prestamo.Dto.LibroDto;
import com.eja.prestamo.Dto.UsuarioDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "eja-libro-service", path = "/libros")
public interface LibroFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "libroListarPorIdCB", fallbackMethod = "fallbackLibroById")
    ResponseEntity<LibroDto> buscarLibro(@PathVariable Long id);

    default ResponseEntity<LibroDto> fallbackLibroById(Integer id, Exception e) {
        LibroDto libroDto = new LibroDto();
        libroDto.setTitulo("Servicio de libro no disponible KR :C");
        return ResponseEntity.ok(libroDto);
    }


    @PutMapping("/{id}")
    ResponseEntity<LibroDto> actualizarLibro(@PathVariable Long id, @RequestBody LibroDto libro);
}
