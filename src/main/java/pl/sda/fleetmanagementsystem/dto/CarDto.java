package pl.sda.fleetmanagementsystem.dto;

import lombok.*;

import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Integer id;
    private String brand;
    private String model;
    private String productionYear;
    private String mileage;
    private Double engineCapacity;
    private DriverDto driverDto;
    private TechnicalInspectionDto technicalInspectionDto;
    private Set<CarAccidentDto> carAccidentsDtos;


}
