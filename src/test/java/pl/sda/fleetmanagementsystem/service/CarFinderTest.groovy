package pl.sda.fleetmanagementsystem.service


import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.sda.fleetmanagementsystem.dto.NewCarDto
import pl.sda.fleetmanagementsystem.model.Car
import pl.sda.fleetmanagementsystem.model.Driver
import pl.sda.fleetmanagementsystem.repository.CarRepository
import pl.sda.fleetmanagementsystem.repository.DriverRepository
import pl.sda.fleetmanagementsystem.repository.DrivingLicenseRepository
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * @author Mariusz Kowalczuk
 */
@SpringBootTest
class CarFinderTest extends Specification {
    def car1 = saveCar(1)
    def car2 = saveCar(2)
    def car3 = saveCar(3)
//    def user = new User(1, "adsd", "asda", null)
//    def drivingLicense = new DrivingLicense(1, LocalDate.of(2020, 12, 12), "2345", driver)
//
//    def driver2 = new Driver(2,
//            user, drivingLicense, null, null)

    //def car4 = new Car(4, "Opel", "Insignia", "2000", "123412", 1.4, LocalDate.of(2020, 12, 12), driver2, null)

    def driver = saveDriver(1)


    @Autowired
    private CarRepository carRepository

    @Autowired
    private DriverRepository driverRepository

    @Autowired
    private DrivingLicenseRepository drivingLicenseRepository

    @Subject
    @Autowired
    private CarFinder carFinder

    @Subject
    @Autowired
    private CarService carService

    @Autowired
    private DriverFinder driverFinder


    def "should find all cars from repository"() {
        given:

        carRepository.saveAll([car1, car2, car3])

        when:
        def result = carFinder.findAll()

        then:
        result.size() == 3

    }

    private static Car saveCar(Integer id) {
        Car.builder().id(id).brand("VW").model("Golf").productionYear("2000").mileage("123123").engineCapacity(1.2).build()
    }

    private static Driver saveDriver(Integer id) {
        Driver.builder().id(id).build()
    }

    @Unroll
    def "should find car of id# from repository"() {

        when:
        def result = carFinder.findById(queryId)

        then:
        result.id == id

        where:
        queryId | id
        1       | 1
        2       | 2
        3       | 3
    }

    def "should throw an exception if there's not a car of given id"() {
        when:
        carFinder.findById(5)

        then:
        def error = thrown(IllegalArgumentException)

    }


    def "should list all drivers"() {
        given:
        driverRepository.save(driver)

        when:
        def all = driverFinder.findAll()


        then:
        all.size() == 1
    }

    def "should set Driver of a Car"() {
        when:
        carService.setDriver(1, 1)

        then:
        carRepository.findById(1).orElse(new Car()).id == 1
    }

    def "should find car by a driver id"() {
        when:
        def cars = carFinder.findByDriverId(1)

        then:
        cars.size() == 1
        cars.id.contains(car1.id)

    }

    def "should create car"() {
        given:
        def size = carRepository.findAll().size()

        when:
        carService.create(Mockito.mock(NewCarDto))

        then:
        carRepository.findAll().size() == size + 1

    }

    def "should find available cars"() {


    }
}
