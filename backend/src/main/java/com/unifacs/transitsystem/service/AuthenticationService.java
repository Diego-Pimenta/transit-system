package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.request.AuthenticationRequestDto;
import com.unifacs.transitsystem.model.dto.request.CreateUserRequestDto;
import com.unifacs.transitsystem.model.dto.response.AuthenticationResponseDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    UserResponseDto register(CreateUserRequestDto createUserRequestDto);
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
    void logout(HttpServletRequest request);
}
