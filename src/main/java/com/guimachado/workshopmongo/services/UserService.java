package com.guimachado.workshopmongo.services;

import com.guimachado.workshopmongo.domain.User;
import com.guimachado.workshopmongo.repository.UserRepository;
import com.guimachado.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository rep;

    public List<User> findAll() {
        return rep.findAll();
    }

    public User findById(String id) {
        Optional<User> user = rep.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}
