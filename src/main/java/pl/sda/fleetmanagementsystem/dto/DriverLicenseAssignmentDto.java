package pl.sda.fleetmanagementsystem.dto;

import lombok.Data;

/**
 * @author Mariusz Kowalczuk
 */
@Data
public class DriverLicenseAssignmentDto {
    private String driverId;
    private String expireDate;
    private String number;
}
