package org.example.geekbrains.lesson7.service.user;

import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto findById(Long id);
    User getById(Long id);
    List<UserDto> findAll();
    UserDto save(UserDto dto);
    User auth(String name, String password);
    void delete(Long id);
    void update(User dto);
    void saveAndSet(User dto);
}

