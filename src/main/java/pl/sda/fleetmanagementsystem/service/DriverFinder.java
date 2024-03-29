package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.DriverDto;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverFinder {

    private final DriverRepository driverRepository;

    public Set<DriverDto> findAll() {
        return driverRepository.findAll().stream().map(Driver::toDto).collect(Collectors.toSet());
    }

    public DriverDto findById(Integer id) {
        return driverRepository.findById(id).map(Driver::toDto).orElse(null);
    }

    public DriverDto findByUserId(Integer id){return driverRepository.findByUserId(id).map(Driver::toDto).orElse(null);}

}
