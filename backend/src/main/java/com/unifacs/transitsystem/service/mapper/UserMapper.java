package com.unifacs.transitsystem.service.mapper;

import com.unifacs.transitsystem.model.dto.request.CreateUserRequestDto;
import com.unifacs.transitsystem.model.dto.request.UpdateUserRequestDto;
import com.unifacs.transitsystem.model.dto.response.UserResponseDto;
import com.unifacs.transitsystem.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring", imports = StringUtils.class)
public interface UserMapper {

    @Mapping(target = "role", constant = "USER")
    User createUserRequestDtoToUser(CreateUserRequestDto createUserRequestDto);

    @Mappings({
            @Mapping(
                    target = "address",
                    expression = "java(StringUtils.hasText(updateUserRequestDto.address()) ? updateUserRequestDto.address() : user.getAddress())"
            ),
            @Mapping(
                    target = "phoneNumber",
                    expression = "java(StringUtils.hasText(updateUserRequestDto.phoneNumber()) ? updateUserRequestDto.phoneNumber() : user.getPhoneNumber())"
            ),
            @Mapping(
                    target = "email",
                    expression = "java(StringUtils.hasText(updateUserRequestDto.email()) ? updateUserRequestDto.email() : user.getEmail())"
            ),
            @Mapping(
                    target = "password",
                    expression = "java(StringUtils.hasText(updateUserRequestDto.password()) ? updateUserRequestDto.password() : user.getPassword())"
            ),
            @Mapping(
                    target = "authorities",
                    ignore = true
            )
    })
    User updateUserRequestDtoToUser(User user, UpdateUserRequestDto updateUserRequestDto);

    UserResponseDto userToUserResponseDto(User user);
}
