package org.alexiskyline.motorcycle.service;

import lombok.RequiredArgsConstructor;
import org.alexiskyline.motorcycle.entity.Motorcycle;
import org.alexiskyline.motorcycle.repository.MotorcycleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorcycleService implements IMotorcycleService {
    private final MotorcycleRepository motorcycleRepository;

    @Override
    @Transactional
    public Motorcycle save(Motorcycle motorcycle) {
        return this.motorcycleRepository.save(motorcycle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Motorcycle> findAll() {
        return this.motorcycleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Motorcycle findById(Integer id) {
        return this.motorcycleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Motorcycle> findAllByUserId(Integer id) {
        return this.motorcycleRepository.findByUserId(id);
    }
}