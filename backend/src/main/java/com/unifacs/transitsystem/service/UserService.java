package com.unifacs.transitsystem.service;

import com.unifacs.transitsystem.model.dto.request.CreateUserRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateUserRequestDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(CreateUserRequestDto createUserRequestDto);
    UserResponseDto getUser(UUID userId);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(UUID userId, UpdateUserRequestDto updateUserRequestDto);
    void deleteUser(UUID userId);
}
