package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
