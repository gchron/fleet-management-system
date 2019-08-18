package pl.sda.fleetmanagementsystem.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.sda.fleetmanagementsystem.dto.CarDto;
import pl.sda.fleetmanagementsystem.dto.NewCarDto;
import pl.sda.fleetmanagementsystem.model.Car;
import pl.sda.fleetmanagementsystem.repository.CarRepository;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

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
    public void createCarShouldSaveCar() {
        Car car1 = new Car();
        car1.setId(1);
        car1.setModel("Golf");
        Car car2 = new Car();
        car2.setId(2);
        car2.setModel("Fox");
        List<CarDto> cars = new ArrayList<>();
        cars.add(car1.toDto());
        cars.add(car2.toDto());
        when(carRepository.save(car1)).thenReturn(car1);
        when(carRepository.save(car2)).thenReturn(car2);

        when(carRepository.findAll()).thenReturn(cars.stream().map(CarDto::toEntity).collect(Collectors.toList()));
        Car car3 = carService.create(NewCarDto.builder().model("Golf").build());
        car3.setId(1);
        Car car4 = carService.create(NewCarDto.builder().model("Fox").build());
        Assert.assertEquals("Golf", car3.getModel());
        Assert.assertEquals((Integer) 1, car3.getId());
        Assert.assertNotEquals("Fox", car3.getModel());
        Assertions.assertEquals(2, carRepository.findAll().size());


    }

    @Test
    public void shouldFindCarByItsID() {
        Car car1 = new Car();
        car1.setId(1);
        car1.setModel("Golf");
        Car car2 = new Car();
        car2.setId(2);
        car2.setModel("Fox");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        when(carRepository.save(car1)).thenReturn(car1);
        when(carRepository.save(car2)).thenReturn(car2);
        Car savedCar1 = carService.create(NewCarDto.builder().model("Golf").build());
        Car savedCar2 = carService.create(NewCarDto.builder().model("Fox").build());
//


        when(carRepository.findById(1)).thenReturn(Optional.of(cars.get(0)));
        Assertions.assertEquals("Golf", carFinder.findById(1).getModel());


    }


    @Test
    public void deleteShouldDeleteCar() {
        Car car1 = new Car();
        car1.setId(1);
        car1.setModel("Golf");
        Car car2 = new Car();
        car2.setId(2);
        car2.setModel("Fox");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        when(carRepository.save(car1)).thenReturn(car1);
        when(carRepository.save(car2)).thenReturn(car2);
        when(carRepository.findById(1)).thenReturn(Optional.of(cars.get(0)));

//        when(carRepository.findById(1)).thenReturn(Optional.of(cars.get(0)));
//
        when(carRepository.findAll()).thenReturn(cars);
        doNothing().when(carRepository).delete(car1);
        cars.remove(car1);

        Car car3 = carService.create(NewCarDto.builder().model("Golf").build());
        Car car4 = carService.create(NewCarDto.builder().model("Fox").build());
        car3.setId(1);
        carService.delete(1);
        verify(carRepository).delete(car1);
        Assert.assertEquals(1, carRepository.findAll().size());


    }


}

   


