package org.alexiskyline.motorcycle.controller;

import lombok.RequiredArgsConstructor;
import org.alexiskyline.motorcycle.entity.Motorcycle;
import org.alexiskyline.motorcycle.service.IMotorcycleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorcycle")
@RequiredArgsConstructor
public class MotorcycleController {
    private final IMotorcycleService motorcycleService;

    @PostMapping
    public ResponseEntity<Motorcycle> saveMotorcycle(@RequestBody Motorcycle motorcycle) {
        Motorcycle newMotorcycle = this.motorcycleService.save(motorcycle);
        return ResponseEntity.ok(newMotorcycle);
    }

    @GetMapping
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {
        List<Motorcycle> motorcycleList = this.motorcycleService.findAll();
        if (motorcycleList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorcycleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable Integer id) {
        Motorcycle foundMotorcycle = this.motorcycleService.findById(id);
        if (foundMotorcycle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundMotorcycle);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Motorcycle>> getAllMotorcyclesByUserId(@PathVariable Integer id) {
        List<Motorcycle> motorcycleList = this.motorcycleService.findAllByUserId(id);
        if (motorcycleList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorcycleList);
    }
}