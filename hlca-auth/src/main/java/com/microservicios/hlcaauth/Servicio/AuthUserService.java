package com.microservicios.hlcaauth.Servicio;

import com.microservicios.hlcaauth.DTO.AuthUserDto;
import com.microservicios.hlcaauth.Entidad.AuthUser;
import com.microservicios.hlcaauth.Entidad.TokenDto;

public interface AuthUserService {
    public AuthUser save(AuthUserDto authUserDto);


    public TokenDto login(AuthUserDto authUserDto);


    public TokenDto validate(String token);
}
