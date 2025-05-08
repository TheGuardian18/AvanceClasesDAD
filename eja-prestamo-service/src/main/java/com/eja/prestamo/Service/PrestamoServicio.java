package com.eja.prestamo.Service;



import com.eja.prestamo.Entity.Prestamo;

import java.util.List;

public interface PrestamoServicio {


    List<Prestamo> Listar();
    Prestamo Buscar(Long id);
    Prestamo Guardar(Prestamo prestamo);
    Prestamo Actualizar(Prestamo prestamo);
    Prestamo Eliminar(Prestamo prestamo);


}
