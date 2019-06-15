package pl.sda.fleetmanagementsystem.service;

import org.springframework.data.repository.CrudRepository;
import pl.sda.fleetmanagementsystem.model.Driver;

public interface DriverService extends CrudRepository<Driver, Integer> {
}
