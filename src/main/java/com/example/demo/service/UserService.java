package com.example.demo.service;

import com.example.demo.repository.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getCreatedUsers() {
        return userRepository.findAll();
    }

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with this id does not exist");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public User update(Long id, String email, String name) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with this id does not exist"));

        if (email != null) {
            user.setEmail(email);
            if (userRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("User with this email already exists");
            }
        }

        if (name != null) {
            user.setName(name);
        }
        return user;
    }
}
