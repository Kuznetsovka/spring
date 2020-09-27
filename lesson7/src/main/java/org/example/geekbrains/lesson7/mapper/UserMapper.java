package org.example.geekbrains.lesson7.mapper;

import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {OrderMapper.class})
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "listOrders", target = "orders")
    User toUser(UserDto dto);
    List<User> toUserList(List<UserDto> users);

    @InheritInverseConfiguration
    UserDto fromUser(User user);

    List<UserDto> fromUserList(List<User> users);


}
