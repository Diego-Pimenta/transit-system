package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.model.dto.request.AuthenticationRequestDto;
import com.unifacs.transitsystem.model.dto.response.AuthenticationResponseDto;
import com.unifacs.transitsystem.model.entity.User;
import com.unifacs.transitsystem.repository.UserRepository;
import com.unifacs.transitsystem.service.AuthenticationService;
import com.unifacs.transitsystem.service.mapper.AuthenticationMapper;
import com.unifacs.transitsystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;

    private final AuthenticationMapper mapper;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.cpf(),
                        authenticationRequestDto.password()
                )
        );
        var user = mapper.authenticationRequestDtoToUser(authenticationRequestDto);
        user = repository.findByCpf(user.getCpf())
                .orElseThrow();
        jwtUtil.generateToken(user);
        return mapper.userToAuthenticationResponseDto(user);
    }
}
