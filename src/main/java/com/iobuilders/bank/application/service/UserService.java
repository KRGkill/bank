package com.iobuilders.bank.application.service;

import com.iobuilders.bank.application.repository.UserRepository;
import com.iobuilders.bank.domain.User;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User getUserById(int id) throws EntityNotFoundException {
        return userRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find User with id: " + id));
    }

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
