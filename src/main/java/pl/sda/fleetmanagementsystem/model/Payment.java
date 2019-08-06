package pl.sda.fleetmanagementsystem.model;

import lombok.*;
import pl.sda.fleetmanagementsystem.dto.PaymentDto;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Mariusz Kowalczuk
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal value;
    private boolean settled;
    @OneToOne
    private Driver driver;

    public PaymentDto toDto() {
        return PaymentDto.builder()
                .id(id)
                .settled(settled)
                .value(value)
                .driverdto(driver != null ? driver.toDto() : null)
                .build();

    }


}
