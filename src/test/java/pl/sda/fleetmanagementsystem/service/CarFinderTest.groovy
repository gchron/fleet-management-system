package pl.sda.fleetmanagementsystem.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.sda.fleetmanagementsystem.model.Car
import pl.sda.fleetmanagementsystem.repository.CarRepository
import spock.lang.Specification
import spock.lang.Subject

/**
 * @author Mariusz Kowalczuk
 */
@SpringBootTest
class CarFinderTest extends Specification {

    @Autowired
    private CarRepository carRepository

    @Subject
    @Autowired
    private CarFinder carFinder


    def "should find all cars from repository"() {
        given:
        def car1 = Car.builder().id(1).brand("VW").model("Golf").productionYear("2000").mileage("123123").engineCapacity(1.2).build()
        def car2 = Car.builder().id(2).brand("VW").model("Golf").productionYear("2000").mileage("123123").engineCapacity(1.2).build()
        def car3 = Car.builder().id(3).brand("VW").model("Golf").productionYear("2000").mileage("123123").engineCapacity(1.2).build()
        carRepository.saveAll([car1, car2, car3])
        when:
        def result = carFinder.findAll()

        then:
        result.size() == 3

    }
}
