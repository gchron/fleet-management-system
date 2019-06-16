package pl.sda.fleetmanagementsystem.model;

import lombok.Getter;
import lombok.ToString;
import pl.sda.fleetmanagementsystem.dto.DrivingLicenseDto;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Mariusz Kowalczuk
 */
@Entity
@ToString
@Getter
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate expireDate;
    private String number;

    @OneToOne
    private Driver driver;

    public DrivingLicenseDto toDto(){
        return DrivingLicenseDto
                .builder()
                .id(id)
                .expireDate(expireDate)
                .number(number)
                .driverDto(driver.toDto())
                .build();
    }



}
