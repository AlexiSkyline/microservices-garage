package org.alexiskyline.user.service;

import lombok.RequiredArgsConstructor;
import org.alexiskyline.user.entity.User;
import org.alexiskyline.user.feignclients.CarServiceFeignClient;
import org.alexiskyline.user.feignclients.MotorcycleServiceFeignClient;
import org.alexiskyline.user.models.Car;
import org.alexiskyline.user.models.Motorcycle;
import org.alexiskyline.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final CarServiceFeignClient carServiceFeignClient;
    private final MotorcycleServiceFeignClient motorcycleServiceFeignClient;

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
        List<Car> carList = this.restTemplate.getForObject("http://car-service/car/user/" + id, List.class);
        return carList;
    }

    @Override
    public List<Motorcycle> findAllMotorcyclesByUserId(Integer id) {
        List<Motorcycle> motorcycleList = this.restTemplate.getForObject("http://motorcycle-service/motorcycle/user/" + id, List.class);
        return motorcycleList;
    }

    @Override
    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        return this.carServiceFeignClient.saveCar(car);
    }

    @Override
    public Motorcycle saveMotorcycle(int userId, Motorcycle motorcycle) {
        motorcycle.setUserId(userId);
        return this.motorcycleServiceFeignClient.saveMotorcycle(motorcycle);
    }

    @Override
    public Map<String, Object> getAllVehiclesByUserId(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Optional<User> foundUser = this.userRepository.findById(id);
        if (foundUser.isEmpty()) {
            result.put("message", "User not exist");
            return result;
        }
        result.put("User", foundUser.get());

        List<Car> carList = this.carServiceFeignClient.getAllCarsByUserId(id);
        if (carList.isEmpty()) {
            result.put("message", "User has not cars");
            return result;
        }
        result.put("Cars", carList);

        List<Motorcycle> motorcycleList = this.motorcycleServiceFeignClient.getAllMotorcyclesByUserId(id);
        if (motorcycleList.isEmpty()) {
            result.put("message", "User has not motorcycles");
        }
        result.put("Motorcycle", motorcycleList);

        return result;
    }
}