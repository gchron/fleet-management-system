package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.fleetmanagementsystem.dto.NewCarDto;
import pl.sda.fleetmanagementsystem.dto.UpdateCarDto;
import pl.sda.fleetmanagementsystem.model.Car;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.repository.CarRepository;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    public Car create(NewCarDto carDto) {
        Car car = Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .productionYear(carDto.getProductionYear())
                .mileage(carDto.getMileage())
                .engineCapacity(carDto.getEngineCapacity())
                .build();

        carRepository.save(car);
        return car;
    }


    @Transactional
    public void setDriver(Integer carId, Integer driverId) {
        Car car = carRepository.findById(carId).orElseThrow(IllegalArgumentException::new);
        Driver driver = driverRepository.findById(driverId).orElseThrow(IllegalArgumentException::new);
        car.setDriver(driver);
        Set<Car> cars = driver.getCars()!=null?driver.getCars():new HashSet<>();
        cars.add(car);
    }

    @Transactional
    public void setTechnicalInspection(Integer carId, String dateOfNextInspection) {
        Car car = carRepository.findById(carId).orElseThrow(IllegalArgumentException::new);

        car.setDateOfNextInspection(LocalDate.parse(dateOfNextInspection));
    }

    public void delete(Integer carId) {
        carRepository.delete(carRepository.findById(carId).orElseThrow(IllegalArgumentException::new));
    }

    @Transactional
    public void update(UpdateCarDto carDto) {
        Car car = carRepository.findById(carDto.getId()).orElseThrow(IllegalArgumentException::new);
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setEngineCapacity(carDto.getEngineCapacity());
        car.setMileage(carDto.getMileage());
        car.setProductionYear(carDto.getProductionYear());

    }


}

