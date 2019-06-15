package pl.sda.fleetmanagementsystem.service;


import pl.sda.fleetmanagementsystem.dto.DriverDto;
import pl.sda.fleetmanagementsystem.model.Driver;

import java.util.Set;

public interface DriverService {

    Set<DriverDto> findAll();

    DriverDto findById(Integer id);
}
