package org.anyframe.cloud.auth.service.impl;

import org.anyframe.cloud.auth.repository.UserRepository;
import org.anyframe.cloud.auth.repository.domain.UserResource;
import org.anyframe.cloud.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Hahn on 2015-12-04.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResource getUserById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public Collection<UserResource> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResource create(UserResource form) {
        UserResource user = new UserResource();
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

}