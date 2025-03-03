package com.guimachado.workshopmongo.services;

import com.guimachado.workshopmongo.domain.User;
import com.guimachado.workshopmongo.dto.UserDTO;
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

    public User insert(User obj) {
        return rep.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        rep.deleteById(id);
    }

    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
}
