package com.example.Crud.controllers;

import com.example.Crud.entities.Car;
import com.example.Crud.repositorys.CarRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/")
    public Car createCar(@RequestBody Car car){

        return carRepository.saveAndFlush(car);
    }

    @GetMapping("/")
    public List<Car> getAllCAr(){

        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getSingleCar(@PathVariable long id){

        if (carRepository.existsById(id)){

            return carRepository.getById(id);
        }

        return new Car();
    }

    @PutMapping("/{id}")
    public Car updateCarType(@PathVariable long id, @RequestBody Car car){

        if (carRepository.existsById(id)){

            car.setId(id);
            return carRepository.saveAndFlush(car);
        }

        return new Car();
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable long id) {

        HttpStatus httpStatus = HttpStatus.CONFLICT;

        if (carRepository.existsById(id)){

            carRepository.deleteById(id);
        }

        else{

            httpStatus.toString();
        }
    }

    @DeleteMapping("/")
    public void deleteAll(){

        carRepository.deleteAll();
    }



}
