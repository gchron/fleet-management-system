package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.sda.fleetmanagementsystem.model.DrivingLicense;

import java.time.LocalDate;

/**
 * @author Mariusz Kowalczuk
 */
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class DrivingLicenseDto {
    private Integer id;
    private LocalDate expireDate;
    private String number;

    public DrivingLicense toEntity() {
        return DrivingLicense
                .builder()
                .expireDate(expireDate)
                .number(number)
                .build();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
