package pl.sda.fleetmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.dto.DriverDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
public class Driver extends User {

    @OneToOne
    private DrivingLicense drivingLicense;

    @OneToMany
    private Set<Car> cars;

    @OneToMany
    private Set<PetrolBill> bills;

    public DriverDto toDto(){
        return DriverDto.builder()
                .id(getId())
                .userName(getUserName())
                .password(getPassword())
                .drivingLicenseDto(drivingLicense.toDto())
                .carsDtos(cars.stream().map(car -> car.toDto()).collect(Collectors.toSet()))
                .petrolBillDtos(bills.stream().map(bill -> bill.toDto()).collect(Collectors.toSet()))
                .build();


    }

}
