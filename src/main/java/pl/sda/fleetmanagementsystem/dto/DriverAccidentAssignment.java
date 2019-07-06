package pl.sda.fleetmanagementsystem.dto;

import lombok.Data;

/**
 * @author Mariusz Kowalczuk
 */
@Data
public class DriverAccidentAssignment {
    private Integer carId;
    private Integer driverId;
    private String accidentDate;
    private String description;


}
