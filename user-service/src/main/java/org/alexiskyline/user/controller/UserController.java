package org.alexiskyline.user.controller;

import lombok.RequiredArgsConstructor;
import org.alexiskyline.user.entity.User;
import org.alexiskyline.user.models.Car;
import org.alexiskyline.user.models.Motorcycle;
import org.alexiskyline.user.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = this.userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = this.userService.findAll();
        if (allUser.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User foundUser = this.userService.findById(id);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<List<Car>> getAllCarsByUserId(@PathVariable Integer id) {
        List<Car> carList = this.userService.findAllCarsByUserId(id);
        if (carList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carList);
    }

    @GetMapping("/motorcycle/{id}")
    public ResponseEntity<List<Motorcycle>> getAllMotorcyclesByUserId(@PathVariable Integer id) {
        List<Motorcycle> motorcycleList = this.userService.findAllMotorcyclesByUserId(id);
        if (motorcycleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motorcycleList);
    }

    @PostMapping("/car/{id}")
    public ResponseEntity<Car> saveCar(@PathVariable Integer id, @RequestBody Car car) {
        Car newCar = this.userService.saveCar(id, car);
        return ResponseEntity.ok(newCar);
    }

    @PostMapping("/motorcycle/{id}")
    public ResponseEntity<Motorcycle> saveMotorcycle(@PathVariable Integer id, @RequestBody Motorcycle motorcycle) {
        Motorcycle newMotorcycle = this.userService.saveMotorcycle(id, motorcycle);
        return ResponseEntity.ok(newMotorcycle);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Map<String, Object>> getAllVehiclesByUserId(@PathVariable Integer id) {
        Map<String, Object> response = this.userService.getAllVehiclesByUserId(id);
        return ResponseEntity.ok(response);
    }
}