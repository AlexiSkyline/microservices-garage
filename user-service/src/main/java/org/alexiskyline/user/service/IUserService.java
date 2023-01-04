package org.alexiskyline.user.service;

import org.alexiskyline.user.entity.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    User findById(Integer id);
}
