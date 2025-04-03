package com.tcars.maincarsservice.service.impl;

import com.tcars.maincarsservice.dao.repository.UserDAO;
import com.tcars.maincarsservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void moveCarToFavorites(UUID carUuid) {

    }
}
