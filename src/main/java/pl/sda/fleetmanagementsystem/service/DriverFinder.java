package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DriverFinder implements DriverService{

    private final DriverRepository driverRepository;

    public Set<Driver> findAll() {
        Set<Driver> drivers = new HashSet<>();
        driverRepository.findAll().forEach(driver -> drivers.add(driver));
        return drivers;
    }

    public Driver findById(Integer id) {
        return driverRepository.findById(id).orElse(null);
    }

    //??
}
