package com.practice.spring.microservice.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {

    private static final List<User> users = new ArrayList<>();
    private static int counter = 0;

    static {
        users.add(new User(++counter, "Srijan", new Date()));
        users.add(new User(++counter, "Saksham", new Date()));
        users.add(new User(++counter, "Sanchita", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++counter);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Optional<User> user = users.stream().filter(userPointer -> userPointer.getId().equals(id)).findFirst();
        return user.orElse(null);
    }
}
