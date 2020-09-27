package org.example.geekbrains.lesson7.service.user;

import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findById(Long id);
    List<UserDto> findAll();
    UserDto save(UserDto dto);
    void delete(Long id);
    void update(User dto);
    void saveAndSet(User dto);
}

