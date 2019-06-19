package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.DrivingLicense;

/**
 * @author Mariusz Kowalczuk
 */
public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Integer> {
}
