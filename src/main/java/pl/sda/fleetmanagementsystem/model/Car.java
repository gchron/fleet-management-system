package pl.sda.fleetmanagementsystem.model;

import pl.sda.fleetmanagementsystem.dto.CarDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private String productionYear;
    private String mileage;
    private Double engineCapacity;

    @ManyToOne
    private Driver driver;

    @OneToOne
    private TechnicalInspection technicalInspection;

    @OneToMany
    private Set<CarAccident> carAccidents;

    public CarDto toDto() {
        return CarDto.builder()
                .id(id)
                .brand(brand)
                .model(model)
                .productionYear(productionYear)
                .mileage(mileage)
                .engineCapacity(engineCapacity)
                .driver(driver)
                .technicalInspection(technicalInspection)
                .carAccidents(carAccidents)
                .build();
    }
}
