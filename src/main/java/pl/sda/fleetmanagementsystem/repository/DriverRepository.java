package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.Driver;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    public Optional<Driver> findByUserId(Integer id);

}
