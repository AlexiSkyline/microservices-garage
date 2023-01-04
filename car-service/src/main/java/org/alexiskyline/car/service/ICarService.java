package org.alexiskyline.car.service;

import org.alexiskyline.car.entity.Car;

import java.util.List;

public interface ICarService {
    Car save(Car car);
    List<Car> findAll();
    Car findById(Integer id);
    List<Car> findAllByUserId(Integer id);
}
