package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.Car;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarDto {
    private Integer id;
    private String brand;
    private String model;
    private String productionYear;
    private String mileage;
    private Double engineCapacity;
    //private DriverDto driverDto;
    private LocalDate dateOfNextInspection;
    private Set<CarAccidentDto> carAccidentsDtos;

    public Car toEntity() {
        return Car.builder()
                .brand(brand)
                .model(model)
                .productionYear(productionYear)
                .mileage(mileage)
                .engineCapacity(engineCapacity)
                .dateOfNextInspection(dateOfNextInspection)
                //.driver(driverDto!=null? driverDto.toEntity():null)
//                .carAccidents(carAccidentsDtos.stream().map(carAccidentDto -> carAccidentDto.toEntity()).collect(Collectors.toSet()))
                .build();
    }

    public boolean isAvailable() {
        return (carAccidentsDtos.isEmpty()) || carAccidentsDtos.stream().allMatch(CarAccidentDto::isSettled);

    }


}
