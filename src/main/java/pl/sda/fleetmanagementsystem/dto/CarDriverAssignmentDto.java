package pl.sda.fleetmanagementsystem.dto;

import lombok.Data;

/**
 * @author Mariusz Kowalczuk
 */
@Data
public class CarDriverAssignmentDto {
    private Integer carId;
    private Integer driverId;
}
