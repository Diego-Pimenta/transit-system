package com.unifacs.transitsystem.service.mapper;

import com.unifacs.transitsystem.model.dto.response.AuthenticationResponseDto;
import com.unifacs.transitsystem.model.entity.User;
import com.unifacs.transitsystem.util.JwtUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = JwtUtil.class)
public interface AuthenticationMapper {

    @Mappings({
            @Mapping(
                    target = "token",
                    expression = "java(JwtUtil.generateToken(user))"
            ),
            @Mapping(
                    target = "expiresIn",
                    expression = "java(JwtUtil.getExpirationTime())"
            )
    })
    AuthenticationResponseDto userToAuthenticationResponseDto(User user);
}
