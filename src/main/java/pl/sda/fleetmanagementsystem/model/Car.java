package pl.sda.fleetmanagementsystem.model;

import lombok.*;
import pl.sda.fleetmanagementsystem.dto.CarDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private String productionYear;
    private String mileage;
    private Double engineCapacity;
    private LocalDate dateOfNextInspection;

    @ManyToOne
    private Driver driver;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<CarAccident> carAccidents;

    public CarDto toDto() {
        return CarDto.builder()
                .id(id)
                .brand(brand)
                .model(model)
                .productionYear(productionYear)
                .mileage(mileage)
                .engineCapacity(engineCapacity)
                .dateOfNextInspection(dateOfNextInspection)
                //.driverDto(driver != null? driver.toDto():null)
//                .carAccidentsDtos(carAccidents != null?
//                        carAccidents
//                        .stream()
//                        .map(carAccident -> carAccident.toDto())
//                                .collect(Collectors.toSet()):null)
                .build();

    }
}
