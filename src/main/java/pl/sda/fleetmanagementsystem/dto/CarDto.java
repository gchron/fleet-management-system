package pl.sda.fleetmanagementsystem.dto;

import lombok.Builder;
import pl.sda.fleetmanagementsystem.model.CarAccident;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.model.TechnicalInspection;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
public class CarDto {
    private Integer id;
    private String brand;
    private String model;
    private String productionYear;
    private String mileage;
    private Double engineCapacity;
    private Driver driver;
    private TechnicalInspection technicalInspection;
    private Set<CarAccident> carAccidents;


}
