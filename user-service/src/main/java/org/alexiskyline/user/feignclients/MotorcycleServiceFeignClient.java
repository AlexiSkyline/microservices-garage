package org.alexiskyline.user.feignclients;

import org.alexiskyline.user.models.Motorcycle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "motorcycle-service")
@RequestMapping("/motorcycle")
public interface MotorcycleServiceFeignClient {
    @PostMapping
    Motorcycle saveMotorcycle(@RequestBody Motorcycle motorcycle);

    @GetMapping("/user/{id}")
    List<Motorcycle> getAllMotorcyclesByUserId(@PathVariable("id") Integer id);
}