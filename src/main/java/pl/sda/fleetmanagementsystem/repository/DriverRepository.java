package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.sda.fleetmanagementsystem.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

}