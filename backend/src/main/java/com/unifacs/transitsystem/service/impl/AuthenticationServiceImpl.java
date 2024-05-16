package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.RequestHeaderMissingException;
import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.model.dto.request.AuthenticationRequestDto;
import com.unifacs.transitsystem.model.dto.request.CreateUserRequestDto;
import com.unifacs.transitsystem.model.dto.response.AuthenticationResponseDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import com.unifacs.transitsystem.repository.UserRepository;
import com.unifacs.transitsystem.security.SecurityConstants;
import com.unifacs.transitsystem.service.AuthenticationService;
import com.unifacs.transitsystem.service.mapper.AuthenticationMapper;
import com.unifacs.transitsystem.service.mapper.UserMapper;
import com.unifacs.transitsystem.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserServiceImpl userService;

    private final UserRepository repository;

    private final AuthenticationMapper authenticationMapper;

    private final UserMapper userMapper;

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
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var token = jwtUtil.generateToken(user);
        var expiration = jwtUtil.getExpirationTime();

        return authenticationMapper.userToAuthenticationResponseDto(userMapper.userToUserResponseDto(user), token, expiration);
    }

    @Override
    public void logout(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            var token = authHeader.substring(7);
            tokenBlacklist.addToBlacklist(token);
        } else {
            throw new RequestHeaderMissingException("Authorization header is missing");
        }
    }
}
