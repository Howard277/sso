package com.ms.web2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ms.web2.bean.User;
import com.ms.web2.repository.UserRepository;

@Transactional
@Component("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void saveUser(List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            userRepository.save(list.get(i));
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
