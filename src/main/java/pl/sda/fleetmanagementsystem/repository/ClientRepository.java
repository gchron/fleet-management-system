package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.Client;

/**
 * @author Mariusz Kowalczuk
 */

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
