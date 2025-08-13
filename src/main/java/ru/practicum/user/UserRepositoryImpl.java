package ru.practicum.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(long userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(getId());
        }
        users.removeIf(existingUser -> existingUser.getId().equals(user.getId()));
        users.add(user);
        return user;
    }

    @Override
    public void deleteById(long userId) {
        users.removeIf(user -> user.getId() == userId);
    }

    private long getId() {
        long lastId = users.stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0);
        return lastId + 1;
    }
}