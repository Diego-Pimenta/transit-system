package com.unifacs.transitsystem.service.mapper;

import com.unifacs.transitsystem.model.dto.request.AuthenticationRequestDto;
import com.unifacs.transitsystem.model.dto.response.AuthenticationResponseDto;
import com.unifacs.transitsystem.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

//    User authenticationRequestDtoToUser(AuthenticationRequestDto authenticationRequestDto);

    AuthenticationResponseDto userToAuthenticationResponseDto(User user);
}
