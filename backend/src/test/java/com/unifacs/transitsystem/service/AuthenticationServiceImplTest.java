package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.model.dto.request.AuthenticationRequestDto;
import com.unifacs.transitsystem.model.dto.response.AuthenticationResponseDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import com.unifacs.transitsystem.model.entity.Role;
import com.unifacs.transitsystem.model.entity.User;
import com.unifacs.transitsystem.repository.UserRepository;
import com.unifacs.transitsystem.service.impl.AuthenticationServiceImpl;
import com.unifacs.transitsystem.service.mapper.AuthenticationMapper;
import com.unifacs.transitsystem.service.mapper.UserMapper;
import com.unifacs.transitsystem.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private AuthenticationMapper authenticationMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticate_success() {
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto("12345678902", "password1");

        User user = new User();
        user.setCpf("12345678902");

        UserResponseDto userResponseDto = new UserResponseDto(
                UUID.randomUUID(),
                "12345678902",
                "Jane Smith",
                "456 Elm St",
                "987-654-3210",
                "jane@example.com",
                "$2a$10$l0.KGvltAgzJ4zf/MMWxk.5AAd8EaQrFJgMPkCTcG9KdFRtxahrg.",
                Role.USER
        );
        String token = "mockToken";
        long expiresIn = 1000L;

        when(repository.findByCpf(requestDto.cpf()))
                .thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(user))
                .thenReturn(token);
        when(jwtUtil.getExpirationTime())
                .thenReturn(expiresIn);
        when(userMapper.userToUserResponseDto(user))
                .thenReturn(userResponseDto);

        AuthenticationResponseDto expectedResponse = new AuthenticationResponseDto(
                userResponseDto,
                token,
                expiresIn
        );
        when(authenticationMapper.userToAuthenticationResponseDto(
                userResponseDto,
                token,
                expiresIn
        )).thenReturn(expectedResponse);

        AuthenticationResponseDto result = authenticationService.authenticate(requestDto);

        assertNotNull(result);
        assertEquals(expectedResponse, result);

        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(repository, times(1))
                .findByCpf(requestDto.cpf());
        verify(jwtUtil, times(1))
                .generateToken(user);
        verify(jwtUtil, times(1))
                .getExpirationTime();
    }

    @Test
    void authenticate_failure() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto("12345678900", "password");

        doThrow(new RuntimeException("Authentication failed"))
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(
                ResourceNotFoundException.class,
                () -> authenticationService.authenticate(authenticationRequestDto)
        );

        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(repository, times(0))
                .findByCpf(authenticationRequestDto.cpf());
        verify(jwtUtil, times(0))
                .generateToken(any());
        verify(jwtUtil, times(0))
                .getExpirationTime();
    }
}
