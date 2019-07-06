package pl.sda.fleetmanagementsystem.model;

import lombok.*;
import pl.sda.fleetmanagementsystem.dto.CarAccidentDto;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
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
    private boolean settled;

    public CarAccidentDto toDto(){
        return CarAccidentDto
                .builder()
                .id(id)
                //.carDto(car.toDto())
                //.driverDto(driver.toDto())
                .accidentDate(accidentDate)
                .description(description)
                .settled(settled)
                .build();
    }

}
