package com.guimachado.workshopmongo.config;

import com.guimachado.workshopmongo.domain.Post;
import com.guimachado.workshopmongo.domain.User;
import com.guimachado.workshopmongo.dto.AuthorDTO;
import com.guimachado.workshopmongo.dto.CommentDTO;
import com.guimachado.workshopmongo.repository.PostRepository;
import com.guimachado.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Post post1 = new Post(null, Date.from(LocalDate.parse("15/03/2025", formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()), "Partiu Viagem", "Vou viajar para São Paulo, partiu abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, Date.from(LocalDate.parse("25/06/2025", formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", Date.from(LocalDate.parse("16/03/2025", formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", Date.from(LocalDate.parse("16/03/2025", formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Date.from(LocalDate.parse("26/06/2025", formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
