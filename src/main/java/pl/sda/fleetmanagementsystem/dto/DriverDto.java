package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.model.Car;
import pl.sda.fleetmanagementsystem.model.DrivingLicense;
import pl.sda.fleetmanagementsystem.model.PetrolBill;

import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

    private Integer id;
    private String userName;
    private String password;
    private DrivingLicense drivingLicense;
    private Set<Car> cars;
    private Set<PetrolBill> bills;

}
