package org.alexiskyline.user.service;

import org.alexiskyline.user.entity.User;
import org.alexiskyline.user.models.Car;
import org.alexiskyline.user.models.Motorcycle;

import java.util.List;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    User findById(Integer id);

    // * RestTemplate
    List<Car> findAllCarsByUserId(Integer id);
    List<Motorcycle> findAllMotorcyclesByUserId(Integer id);
}