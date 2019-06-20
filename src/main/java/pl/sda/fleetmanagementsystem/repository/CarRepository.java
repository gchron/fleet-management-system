package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.Car;

import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
public interface CarRepository extends JpaRepository<Car, Integer> {

    Set<Car> findByDriverId(Integer driverId);

}
