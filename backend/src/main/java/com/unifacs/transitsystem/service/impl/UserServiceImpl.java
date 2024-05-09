package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.UserAlreadyExistsException;
import com.unifacs.transitsystem.model.dto.request.CreateUserRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateUserRequestDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import com.unifacs.transitsystem.repository.UserRepository;
import com.unifacs.transitsystem.service.UserService;
import com.unifacs.transitsystem.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        try {
            var user = mapper.createUserRequestDtoToUser(createUserRequestDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            var createdUser = repository.save(user);
            return mapper.userToUserResponseDto(createdUser);
        } catch (Exception e) {
            throw new UserAlreadyExistsException("Already exists an user with this CPF");
        }
    }

    @Override
    public UserResponseDto getUser(UUID userId) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        var users = repository.findAll();
        return users
                .stream()
                .map(mapper::userToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(UUID userId, UpdateUserRequestDto updateUserRequestDto) {
        return null;
    }

    @Override
    public void deleteUser(UUID userId) {

    }
}
