package pl.sda.fleetmanagementsystem.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPaymentDto {
    private BigDecimal value;
    private DriverDto driverDto;
    private boolean settled;

}
