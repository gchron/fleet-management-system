package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.CarAccident;

/**
 * @author Mariusz Kowalczuk
 */
public interface CarAccidentRepository extends JpaRepository<CarAccident, Integer> {
}
