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
import pl.sda.fleetmanagementsystem.dto.CreateUserAssignment;
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
    private static final CreateUserAssignment newDriver = new CreateUserAssignment("driver", "pass", "bla@vla.com", 1);
    private static final CreateUserAssignment newAdministrator = new CreateUserAssignment("admininstrator", "pass", "bla@vla.com", 2);
    private static final CreateUserAssignment newClient = new CreateUserAssignment("client", "pass", "bla@vla.com", 3);

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private CarFinder carFinder;
    @Autowired
    private CarService carService = new CarService(carRepository, driverRepository);
    @Autowired
    private DriverFinder driverFinder;
    @Autowired
    private UserFinder userFinder;
    @Autowired
    private DriverService driverService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DrivingLicenseRepository drivingLicenseRepository;
    @Autowired
    private PetrolBillRepository petrolBillRepository;
    @Autowired
    private CarAccidentRepository carAccidentRepository;
    @Autowired
    private UserService userService;


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
        carService.delete(2);
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

    //gdy puście się tylko ten, to przechodzi, gdy wszystkie - to nie, illegalArgumentEx,
    // tak jakby nie zdanjdowało samochodu o id 1...
    @Test
    public void setTechnicalInspectionDateShouldSetDateOdTechnicalInspection() {

        carService.create(newCarDto);
        carService.setTechnicalInspection(1, "2000-01-01");
        Assertions.assertEquals(LocalDate.parse("2000-01-01"), carRepository.findById(1).orElse(null).getDateOfNextInspection());
    }
    
    @Test
    public void registeringDriverShouldCreateNewUserAndNewDriver(){
        Assertions.assertEquals(1, userRepository.findAll().size());
        Assertions.assertEquals(0, driverRepository.findAll().size());
        userService.register(newDriver);
        Assertions.assertEquals(2, userRepository.findAll().size());
        Assertions.assertEquals(1, driverRepository.findAll().size());



        
    }
    @Test
    public void registeringAdministratorShouldCreateNewUserAndNewAdministrator(){
        Assertions.assertEquals(2, userRepository.findAll().size());
        Assertions.assertEquals(0, administratorRepository.findAll().size());
        userService.register(newAdministrator);
        Assertions.assertEquals(3, userRepository.findAll().size());
        Assertions.assertEquals(1, administratorRepository.findAll().size());




    }
    @Test
    public void registeringClientShouldCreateNewUserAndNewClient(){
        Assertions.assertEquals(0, userRepository.findAll().size());
        Assertions.assertEquals(0, clientRepository.findAll().size());
        userService.register(newClient);
        Assertions.assertEquals(1, userRepository.findAll().size());
        Assertions.assertEquals(1, clientRepository.findAll().size());




    }
    

    @Transactional
    @Test
    public void setDrivershouldSetDriverOfCar() {
        carService.create(newCarDto);
        Car car = carRepository.findById(1).orElse(null);
        userService.register(newDriver);

        carService.setDriver(1, 1);
        Integer id = car.getDriver().getId();
        Assertions.assertEquals((Integer)1, id);


    }


}
