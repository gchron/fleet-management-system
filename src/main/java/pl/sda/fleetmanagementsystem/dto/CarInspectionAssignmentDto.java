package pl.sda.fleetmanagementsystem.dto;

import lombok.Data;

/**
 * @author Mariusz Kowalczuk
 */
@Data
public class CarInspectionAssignmentDto {
    private Integer carId;
    private String dateOfNextInspection;
}
