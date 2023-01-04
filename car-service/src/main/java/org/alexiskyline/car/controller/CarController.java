package org.alexiskyline.car.controller;

import lombok.RequiredArgsConstructor;
import org.alexiskyline.car.entity.Car;
import org.alexiskyline.car.service.ICarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final ICarService carService;

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        Car newCar = this.carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAllCar() {
        List<Car> carList = this.carService.findAll();
        if (carList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable Integer id) {
        Car foundUser = this.carService.findById(id);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Car>> findAllCarsByUserId(@PathVariable Integer id) {
        List<Car> carList = this.carService.findAllByUserId(id);
        if (carList == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carList);
    }
}