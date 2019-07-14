package pl.sda.fleetmanagementsystem.model;

import lombok.*;
import pl.sda.fleetmanagementsystem.dto.DriverDto;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User user;

    @OneToOne
    private DrivingLicense drivingLicense;

    @OneToMany
    private Set<Car> cars;

    @OneToMany
    private Set<PetrolBill> bills;

    public DriverDto toDto(){
        return DriverDto.builder()
                .id(id)
                .userDto(user!= null? user.toDto(): null)
                .drivingLicenseDto(drivingLicense != null? drivingLicense.toDto():null)
                .carsDtos(cars!= null? cars.stream().map(car -> car.toDto()).collect(Collectors.toSet()):null)
                .petrolBillDtos(bills!= null? bills.stream().map(PetrolBill::toDto).collect(Collectors.toSet()):null)
                .build();


    }

}
