package org.example.geekbrains.lesson7.service.user;

import org.example.geekbrains.lesson7.dao.ProductDao;
import org.example.geekbrains.lesson7.dao.UserDao;
import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.dto.UserDto;
import org.example.geekbrains.lesson7.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private final EntityManager em;
    private final UserDao dao;
    private final UserMapper mapper = UserMapper.MAPPER;

    public UserServiceImpl(EntityManager em, ProductDao productJpaDAO, UserDao dao) {
        this.em = em;
        this.dao = dao;
    }

    @Transactional
    @Override
    public void saveAndSet(User user){
        User savedUser = dao.save(user);
    }

    @Override
    public UserDto findById(Long id) {
        return mapper.fromUser(dao.getOne (id));
    }

    @Override
    public User getById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public User auth(String username, String password) {
        if(username == null || username.isEmpty()){
            System.out.println("You are not authenticated");
            return null;
        }
        User user = dao.findFirstByName(username);
        if(user == null){
            System.out.println("You are not authenticated");
            return null;
        }
        if(!Objects.equals(password, user.getPassword())){
            System.out.println("You are not authenticated");
            return null;
        }
        System.out.println("You are authenticated");
        return user;
    }

    @Override
    public List<UserDto> findAll() {
        return mapper.fromUserList(dao.findAll());
    }

    @Override
    public UserDto save(UserDto dto) {
        User entity = mapper.toUser(dto);
        User savedEntity = dao.save(entity);
        return mapper.fromUser(savedEntity);
    }

    @Transactional
    @Override
    public void delete(Long id){
        dao.deleteById (id);
    }

    @Transactional
    @Override
    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findFirstByName(username);
    }
}
