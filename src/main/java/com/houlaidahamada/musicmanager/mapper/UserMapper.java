package com.houlaidahamada.musicmanager.mapper;

import com.houlaidahamada.musicmanager.dto.user.UserRegisterDto;
import com.houlaidahamada.musicmanager.dto.user.UserResponseDto;
import com.houlaidahamada.musicmanager.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegisterDto dto);

    UserResponseDto toDto(User user);
}
