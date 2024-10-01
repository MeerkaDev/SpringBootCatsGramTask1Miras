package org.mirasruntime.catsgramtask1miras.controller;

import org.mirasruntime.catsgramtask1miras.exceptions.InvalidEmailException;
import org.mirasruntime.catsgramtask1miras.exceptions.UserAlreadyExistException;
import org.mirasruntime.catsgramtask1miras.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private HashSet<User> users = new HashSet<>();

    @GetMapping
    public HashSet<User> getAllUsers() {
        return users;
    }

    @PostMapping
    public User postUser(@RequestBody User user) {

        if (user.getEmail().isEmpty()) {
            throw new InvalidEmailException("Email can't be empty. Please fill it up!");
        }

        if (users.stream()
                .map(User::getEmail)
                .toList().contains(user.getEmail())) {

            throw new UserAlreadyExistException("This email is occupied by another user!");

        }

        users.add(user);
        return user;
    }

    @PutMapping
    public User putUser(@RequestBody User user) {

        if (user.getEmail().isEmpty()) {
            throw new InvalidEmailException("Email can't be empty. Please fill it up!");
        }

        users.add(user);

        return user;
    }
}