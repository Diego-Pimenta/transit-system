package com.unifacs.transitsystem.service.mapper;

import com.unifacs.transitsystem.model.dto.response.AuthenticationResponseDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

    AuthenticationResponseDto userToAuthenticationResponseDto(UserResponseDto user, String token, long expiresIn);
}
