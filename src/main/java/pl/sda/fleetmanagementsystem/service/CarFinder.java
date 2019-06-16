package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.CarDto;
import pl.sda.fleetmanagementsystem.model.Car;
import pl.sda.fleetmanagementsystem.repository.CarRepository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class CarFinder {

    private final CarRepository carRepository;


    public Set<CarDto> findAll() {
        return carRepository.findAll().stream().map(Car::toDto).collect(Collectors.toSet());
    }
}
