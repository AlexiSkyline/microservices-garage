package org.alexiskyline.car.service;

import lombok.RequiredArgsConstructor;
import org.alexiskyline.car.entity.Car;
import org.alexiskyline.car.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {
    private final CarRepository carRepository;

    @Override
    @Transactional
    public Car save(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Car findById(Integer id) {
        return this.carRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAllByUserId(Integer id) {
        return this.carRepository.findByUserId(id);
    }
}