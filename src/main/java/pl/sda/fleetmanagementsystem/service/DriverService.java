package pl.sda.fleetmanagementsystem.service;


import pl.sda.fleetmanagementsystem.model.Driver;

import java.util.Set;

public interface DriverService {

    Set<Driver> findAll();

    Driver findById(Integer id);
}
