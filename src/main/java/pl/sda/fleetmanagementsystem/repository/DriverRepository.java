package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.fleetmanagementsystem.model.Driver;

public interface DriverRepository extends CrudRepository<Driver, Integer> {

}
