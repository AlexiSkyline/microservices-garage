package org.alexiskyline.car.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Getter @Setter
public class Car {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private Integer userId;
}