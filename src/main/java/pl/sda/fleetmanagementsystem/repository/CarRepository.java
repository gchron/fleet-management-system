package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.sda.fleetmanagementsystem.model.Car;

/**
 * @author Mariusz Kowalczuk
 */
public interface CarRepository extends JpaRepository<Car, Integer> {
}
