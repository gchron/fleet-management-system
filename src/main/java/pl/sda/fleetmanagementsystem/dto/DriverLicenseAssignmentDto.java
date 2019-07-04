package pl.sda.fleetmanagementsystem.dto;

import lombok.Data;

/**
 * @author Mariusz Kowalczuk
 */
@Data
public class DriverLicenseAssignmentDto {
    private Integer userId;
    private String expireDate;
    private String number;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
