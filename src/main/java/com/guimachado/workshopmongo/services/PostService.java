package com.guimachado.workshopmongo.services;

import com.guimachado.workshopmongo.domain.Post;
import com.guimachado.workshopmongo.repository.PostRepository;
import com.guimachado.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository rep;

    public Post findById(String id) {
        Optional<Post> obj = rep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitle(String text) {
        return rep.searchTitle(text);
    }
}
