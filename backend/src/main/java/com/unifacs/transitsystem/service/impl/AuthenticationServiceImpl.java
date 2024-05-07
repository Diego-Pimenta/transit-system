package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.MissingRequestHeaderException;
import com.unifacs.transitsystem.model.dto.request.AuthenticationRequestDto;
import com.unifacs.transitsystem.model.dto.request.CreateUserRequestDto;
import com.unifacs.transitsystem.model.dto.response.AuthenticationResponseDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import com.unifacs.transitsystem.repository.UserRepository;
import com.unifacs.transitsystem.service.AuthenticationService;
import com.unifacs.transitsystem.service.mapper.AuthenticationMapper;
import com.unifacs.transitsystem.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

import static com.unifacs.transitsystem.security.SecurityConstants.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserServiceImpl userService;

    private final UserRepository repository;

    private final AuthenticationMapper mapper;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final InMemoryTokenBlacklist tokenBlacklist;

    @Override
    public UserResponseDto register(CreateUserRequestDto createUserRequestDto) {
        return userService.createUser(createUserRequestDto);
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.cpf(),
                        authenticationRequestDto.password()
                )
        );
        var user = repository.findByCpf(authenticationRequestDto.cpf())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        var token = jwtUtil.generateToken(user);
        var expiration = jwtUtil.getExpirationTime();

        return mapper.userToAuthenticationResponseDto(user, token, expiration);
    }

    @Override
    public void logout(HttpServletRequest request) {
        var authHeader = request.getHeader(HEADER_STRING);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith(TOKEN_PREFIX)) {
            var token = authHeader.substring(7);
            tokenBlacklist.addToBlacklist(token);
        } else {
            throw new MissingRequestHeaderException("Authorization header is missing");
        }
    }
}
