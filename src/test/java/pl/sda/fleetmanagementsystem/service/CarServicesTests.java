package pl.sda.fleetmanagementsystem.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.sda.fleetmanagementsystem.dto.NewCarDto;
import pl.sda.fleetmanagementsystem.model.Car;
import pl.sda.fleetmanagementsystem.repository.CarRepository;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Kowalczuk
 */
public class CarServicesTests {


    CarFinder carFinder;
    CarService carService;
    @Mock
    CarRepository carRepository;
    @Mock
    DriverRepository driverRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        carService = new CarService(carRepository, driverRepository);
        carFinder = new CarFinder(carRepository, driverRepository);
    }

    @Test
    public void createCarShouldAddCarToDB() {
        Assertions.assertEquals(0, carRepository.findAll().size());
        Car car = new Car();
        car.setModel("Golf");
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        carService.create(NewCarDto.builder().model("Golf").build());
        Mockito.when(carRepository.findAll()).thenReturn(cars);
        Assert.assertTrue(carRepository.findAll().stream().map(Car::getModel).anyMatch(s -> s.contains("Golf")));
        Assertions.assertEquals(1, carRepository.findAll().size());


    }
}
