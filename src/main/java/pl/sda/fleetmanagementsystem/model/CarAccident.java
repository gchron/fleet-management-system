package pl.sda.fleetmanagementsystem.model;

import pl.sda.fleetmanagementsystem.dto.CarAccidentDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CarAccident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Driver driver;
    private LocalDate accidentDate;
    private String description;

    public CarAccidentDto toDto(){
        return CarAccidentDto
                .builder()
                .id(id)
                .carDto(car.toDto())
                .driverDto(driver.toDto())
                .accidentDate(accidentDate)
                .description(description)
                .build();
    }

}
