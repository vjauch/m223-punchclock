package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (user.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Crate user: User with username '" + user.getUsername() + "' already exists!");
        }
        if (userRepository.exists(Example.of(user, ExampleMatcher
                .matchingAny()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())))
        ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Crate user: User with username '" + user.getUsername() + "' already exists!");
        }
        return userRepository.saveAndFlush(user);
    }
}
