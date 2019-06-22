package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.Administrator;

/**
 * @author Mariusz Kowalczuk
 */
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
