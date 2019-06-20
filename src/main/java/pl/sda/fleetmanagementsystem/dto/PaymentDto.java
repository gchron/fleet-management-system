package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.Payment;

import java.math.BigDecimal;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Integer id;
    private BigDecimal value;
    private boolean settled;
    private DriverDto driverdto;

    public Payment toEntity() {
        return Payment.builder()
                .value(value)
                .settled(settled)
                .driver(driverdto != null ? driverdto.toEntity() : null)
                .build();
    }

}
