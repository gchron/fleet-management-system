package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.CarDto;
import pl.sda.fleetmanagementsystem.model.Car;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.repository.CarRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class CarFinder implements CarService {

    private CarRepository carRepository;

    @Override
    public Set<CarDto> findAll() {

        return carRepository.findAll().stream().map(Car::toDto).collect(Collectors.toSet());
    }
}
