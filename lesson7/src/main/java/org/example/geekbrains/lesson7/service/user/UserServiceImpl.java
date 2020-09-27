package org.example.geekbrains.lesson7.service.user;

import org.example.geekbrains.lesson7.dao.ProductDao;
import org.example.geekbrains.lesson7.dao.UserDao;
import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.dto.UserDto;
import org.example.geekbrains.lesson7.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
}
