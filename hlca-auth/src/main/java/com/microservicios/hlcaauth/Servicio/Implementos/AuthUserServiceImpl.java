package com.microservicios.hlcaauth.Servicio.Implementos;

import com.microservicios.hlcaauth.DTO.AuthUserDto;
import com.microservicios.hlcaauth.Entidad.AuthUser;
import com.microservicios.hlcaauth.Entidad.TokenDto;
import com.microservicios.hlcaauth.Repositorio.AuthUserRepository;
import com.microservicios.hlcaauth.Seguridad.JwtProvider;
import com.microservicios.hlcaauth.Servicio.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




import java.util.Optional;




@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;


    @Override
    public AuthUser save(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUsername(authUserDto.getUserName());
        if (user.isPresent())
            return null;
        String password = passwordEncoder.encode(authUserDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .build();




        return authUserRepository.save(authUser);
    }




    @Override
    public TokenDto login(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUsername(authUserDto.getUserName());
        if (!user.isPresent())
            return null;
        if (passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }




    @Override
    public TokenDto validate(String token) {
        if (!jwtProvider.validate(token))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if (!authUserRepository.findByUsername(username).isPresent())
            return null;
        return new TokenDto(token);
    }
}
