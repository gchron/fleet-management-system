package pl.sda.fleetmanagementsystem.service;

import pl.sda.fleetmanagementsystem.dto.CarDto;
import pl.sda.fleetmanagementsystem.model.Car;

import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
public interface CarService {

    Set<CarDto> findAll();
}
