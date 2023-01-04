package org.alexiskyline.user.service;

import lombok.RequiredArgsConstructor;
import org.alexiskyline.user.entity.User;
import org.alexiskyline.user.models.Car;
import org.alexiskyline.user.models.Motorcycle;
import org.alexiskyline.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final String PATH = "http://localhost:";

    @Override
    @Transactional
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Car> findAllCarsByUserId(Integer id) {
        List<Car> carList = this.restTemplate.getForObject(this.PATH + "8082/car/user/" + id, List.class);
        return carList;
    }

    @Override
    public List<Motorcycle> findAllMotorcyclesByUserId(Integer id) {
        List<Motorcycle> motorcycleList = this.restTemplate.getForObject(this.PATH + "8083/motorcycle/user/" + id, List.class);
        return motorcycleList;
    }
}