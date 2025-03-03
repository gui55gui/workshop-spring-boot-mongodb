package com.guimachado.workshopmongo.services;

import com.guimachado.workshopmongo.domain.User;
import com.guimachado.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository rep;

    public List<User> findAll() {
        return rep.findAll();
    }
}
