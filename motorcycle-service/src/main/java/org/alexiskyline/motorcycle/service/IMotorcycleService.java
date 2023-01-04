package org.alexiskyline.motorcycle.service;

import org.alexiskyline.motorcycle.entity.Motorcycle;

import java.util.List;

public interface IMotorcycleService {
    Motorcycle save(Motorcycle motorcycle);
    List<Motorcycle> findAll();
    Motorcycle findById(Integer id);
    List<Motorcycle> findAllByUserId(Integer id);
}