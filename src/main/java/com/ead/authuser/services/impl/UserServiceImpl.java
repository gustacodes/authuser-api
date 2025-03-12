package com.ead.authuser.services.impl;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRespository;
import com.ead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRespository;


    @Override
    public List<UserModel> findAll() {
        return userRespository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {
        return userRespository.findById(userId);
    }

    @Override
    public void delete(UserModel user) {
        userRespository.delete(user);
    }

    @Override
    public void save(UserModel userModel) {
        userRespository.save(userModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRespository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRespository.existsByEmail(email);
    }

    @Override
    public Page<UserModel> findAll(Pageable pageable, Specification<UserModel> spec) {
        return userRespository.findAll(spec, pageable);
    }
}
