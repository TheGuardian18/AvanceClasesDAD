package com.eja.prestamo.Feign;


import com.eja.prestamo.Dto.UsuarioDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "eja-usuario-service", path = "/usuarios")
public interface UsuarioFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "usuarioListarPorIdCB", fallbackMethod = "fallbackUsuarioById")
    ResponseEntity<UsuarioDto> buscarUsuario(@PathVariable Long id);
    default ResponseEntity<UsuarioDto> fallbackUsuarioById(Integer id, Exception e) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNombre("Servicio de usuario no disponible KR :C");
        return ResponseEntity.ok(usuarioDto);
    }
}