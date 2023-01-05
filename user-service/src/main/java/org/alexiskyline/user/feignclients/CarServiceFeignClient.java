package org.alexiskyline.user.feignclients;

import org.alexiskyline.user.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
@RequestMapping("/car")
public interface CarServiceFeignClient {
    @PostMapping
    Car saveCar(@RequestBody Car car);

    @GetMapping("/user/{id}")
    List<Car> getAllCarsByUserId(@PathVariable("id") Integer id);
}