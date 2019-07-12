package pl.sda.fleetmanagementsystem;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.fleetmanagementsystem.dto.CarDto;
import pl.sda.fleetmanagementsystem.dto.NewCarDto;
import pl.sda.fleetmanagementsystem.model.Car;
import pl.sda.fleetmanagementsystem.repository.*;
import pl.sda.fleetmanagementsystem.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@PropertySource(value = {"application.properties", "application-ext.properties"},
        ignoreResourceNotFound = true)
@RunWith(SpringRunner.class)
@SpringBootTest
public class FleetManagementSystemApplicationTests {

    private static final NewCarDto newCarDto = new NewCarDto("Volkswagen", "Golf", "2000", "123412", 1.2);


    @Autowired
    private CarFinder carFinder;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverFinder driverFinder;
    @Autowired
    private UserFinder userFinder;
    @Autowired
    private DriverService driverService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DrivingLicenseRepository drivingLicenseRepository;
    @Autowired
    private PetrolBillRepository petrolBillRepository;
    @Autowired
    private CarAccidentRepository carAccidentRepository;


    @Test
    public void createCarShouldCreateCarAndAddToDatabase() {
        List<Car> all = carRepository.findAll();
        Assertions.assertEquals(0, all.size());
        carService.create(newCarDto);
        all = carRepository.findAll();
        Assertions.assertEquals(1, all.size());
        carService.create(newCarDto);
        carService.create(newCarDto);
        all = carRepository.findAll();
        Assertions.assertEquals(3, all.size());


    }

    @Test
    public void deleteCarShouldDeleteCarFromRepository() {
        List<Car> all = carRepository.findAll();
        Assertions.assertEquals(6, all.size());
        carService.delete(1);
        List<Car> all2 = carRepository.findAll();
        Assertions.assertEquals(5, all2.size());
    }

    @Test
    public void findAllCarsShouldListAllCars() {
        carService.create(newCarDto);
        carService.create(newCarDto);
        carService.create(newCarDto);
        Set<CarDto> all = carFinder.findAll();
        List<Car> repositoryAll = carRepository.findAll();
        Assertions.assertEquals(repositoryAll.size(), all.size());

    }

    @Transactional
    @Test
    public void setTechnicalInspectionDateShouldSetDateOdTechnicalInspection() {
        Car car = carRepository.findById(1).orElse(null);
        Assertions.assertEquals(null, car.getDateOfNextInspection());
        carService.setTechnicalInspection(1, "2000-01-01");
        Assertions.assertEquals(LocalDate.parse("2000-01-01"), car.getDateOfNextInspection());
    }


//    @Test
//    public void setDrivershouldSetDriverOfCar(){
//        carService.create(newCarDto);
//        Car car = carRepository.findById(1).orElse(null);
//        Assertions.assertEquals(null, car.getDriver());
//
//
//        carService.setDriver(1, 1 );
//        Assertions.assertEquals((Integer)1, car.getDriver().getId());
//
//
//    }


}
