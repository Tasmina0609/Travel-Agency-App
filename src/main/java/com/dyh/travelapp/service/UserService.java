package com.dyh.travelapp.service;

import com.dyh.travelapp.model.User;
import com.dyh.travelapp.repository.TourRepository;
import com.dyh.travelapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, TourRepository tourRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        String passBCrypt = passwordEncoder.encode(user.getPassword());
        user.setPassword(passBCrypt);
        userRepository.save(user);
    }

    public boolean checkUniqueUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


}
