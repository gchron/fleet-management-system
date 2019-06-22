package pl.sda.fleetmanagementsystem.repository;

/**
 * @author Mariusz Kowalczuk
 */

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.UserRole;


public interface RoleRepository extends JpaRepository<UserRole, Integer> {

}
