package org.alexiskyline.user.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.alexiskyline.user.entity.User;
import org.alexiskyline.user.models.Car;
import org.alexiskyline.user.models.Motorcycle;
import org.alexiskyline.user.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @CircuitBreaker(name = "CarCircuitBreaker", fallbackMethod = "fallBackGetAllCarsByUserId")
    @GetMapping("/car/{id}")
    public ResponseEntity<List<Car>> getAllCarsByUserId(@PathVariable Integer id) {
        List<Car> carList = this.userService.findAllCarsByUserId(id);
        if (carList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carList);
    }

    @CircuitBreaker(name = "MotorcycleCircuitBreaker", fallbackMethod = "fallBackGetAllMotorcyclesByUserId")
    @GetMapping("/motorcycle/{id}")
    public ResponseEntity<List<Motorcycle>> getAllMotorcyclesByUserId(@PathVariable Integer id) {
        List<Motorcycle> motorcycleList = this.userService.findAllMotorcyclesByUserId(id);
        if (motorcycleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motorcycleList);
    }

    @CircuitBreaker(name = "CarCircuitBreaker", fallbackMethod = "fallBackSaveCarByUserId")
    @PostMapping("/car/{id}")
    public ResponseEntity<Car> saveCar(@PathVariable Integer id, @RequestBody Car car) {
        Car newCar = this.userService.saveCar(id, car);
        return ResponseEntity.ok(newCar);
    }

    @CircuitBreaker(name = "MotorcycleCircuitBreaker", fallbackMethod = "fallBackSaveMotorcycleByUserId")
    @PostMapping("/motorcycle/{id}")
    public ResponseEntity<Motorcycle> saveMotorcycle(@PathVariable Integer id, @RequestBody Motorcycle motorcycle) {
        Motorcycle newMotorcycle = this.userService.saveMotorcycle(id, motorcycle);
        return ResponseEntity.ok(newMotorcycle);
    }

    @CircuitBreaker(name = "AllCircuitBreaker", fallbackMethod = "fallBackAllVehiclesByUserId")
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Map<String, Object>> getAllVehiclesByUserId(@PathVariable Integer id) {
        Map<String, Object> response = this.userService.getAllVehiclesByUserId(id);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<List<Car>> fallBackGetAllCarsByUserId(@PathVariable("id") Integer id, RuntimeException ex) {
        return new ResponseEntity("Oops an error occurred when getting the Carts of the user with the user id:" + id, HttpStatus.OK);
    }

    private ResponseEntity<List<Motorcycle>> fallBackGetAllMotorcyclesByUserId(@PathVariable("id") Integer id, RuntimeException ex) {
        return new ResponseEntity("Oops an error occurred when getting the Motorcycles of the user with the user id:" + id, HttpStatus.OK);
    }

    private ResponseEntity<Car> fallBackSaveCarByUserId(@PathVariable("id") Integer id, @RequestBody Car car, RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("Message:", "Oops an error occurred while saving the user's Cart with the user id:" + id);
        response.put("Card-Body", car);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    private ResponseEntity<Motorcycle> fallBackSaveMotorcycleByUserId(@PathVariable("id") Integer id, @RequestBody Motorcycle motorcycle, RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("Message:", "Oops an error occurred while saving the user's Motorcycle with the user id:" + id);
        response.put("Motorcycle-Body", motorcycle);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> fallBackAllVehiclesByUserId(@PathVariable("id") Integer id, RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("Message:", "Oops an error occurred when getting the information of the user Vehicles with the user id:" + id);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}