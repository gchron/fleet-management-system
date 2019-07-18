package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.CarDto;
import pl.sda.fleetmanagementsystem.dto.DriverDto;
import pl.sda.fleetmanagementsystem.model.Car;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.repository.CarRepository;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class CarFinder {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;


    public Set<CarDto> findAll() {
        return carRepository.findAll().stream().map(Car::toDto).collect(Collectors.toSet());
    }

    public CarDto findById(Integer carId) {
        return carRepository.findById(carId).map(Car::toDto).orElseThrow(IllegalArgumentException::new);
    }

    public Set<CarDto> findByDriverId(Integer driverId) {
        return carRepository.findByDriver_Id(driverId).stream().map(Car::toDto).collect(Collectors.toSet());

    }

    public Set<CarDto> findAvailableCars() {
        Set<DriverDto> availableDrivers = driverRepository.findAll().stream().map(Driver::toDto).filter(DriverDto::isAvailable).collect(Collectors.toSet());
        if (availableDrivers.isEmpty()) {
            return new HashSet<>();
        }

         return availableDrivers.stream().flatMap(driverDto -> driverDto.getCarsDtos().stream()).filter(CarDto::isAvailable).collect(Collectors.toSet());


    }
}
