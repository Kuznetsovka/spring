package org.example.geekbrains.lesson7.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.dto.UserDto;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-27T23:15:17+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    private final OrderMapper orderMapper = Mappers.getMapper( OrderMapper.class );

    @Override
    public User toUser(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setOrders( orderMapper.toOrderList( dto.getListOrders() ) );
        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );
        user.setAge( dto.getAge() );
        user.setDate( dto.getDate() );
        user.setId( dto.getId() );

        return user;
    }

    @Override
    public List<User> toUserList(List<UserDto> users) {
        if ( users == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( users.size() );
        for ( UserDto userDto : users ) {
            list.add( toUser( userDto ) );
        }

        return list;
    }

    @Override
    public UserDto fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setListOrders( orderMapper.fromOrderList( user.getOrders() ) );
        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        userDto.setAge( user.getAge() );
        userDto.setDate( user.getDate() );

        return userDto;
    }

    @Override
    public List<UserDto> fromUserList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( fromUser( user ) );
        }

        return list;
    }
}
