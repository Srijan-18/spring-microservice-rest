package com.practice.spring.microservice.user.service;

import com.practice.spring.microservice.user.dao.User;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public User deleteById(int id) {
        Iterator<User> userIterator = users.iterator();

        while(userIterator.hasNext()) {
            User user = userIterator.next();
            if (user.getId().equals(id)) {
                userIterator.remove();
                return user;
            }
        }
        return null;
    }
}
