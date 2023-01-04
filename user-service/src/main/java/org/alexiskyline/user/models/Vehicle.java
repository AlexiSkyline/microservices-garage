package org.alexiskyline.user.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Vehicle {
    private Integer id;
    private String brand;
    private String model;
    private Integer userId;
}
