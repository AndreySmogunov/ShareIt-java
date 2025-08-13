package ru.practicum.user;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(long userId);
    User save(User user);
    void deleteById(long userId);
}