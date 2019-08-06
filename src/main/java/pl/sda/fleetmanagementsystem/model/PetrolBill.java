package pl.sda.fleetmanagementsystem.model;

import lombok.*;
import pl.sda.fleetmanagementsystem.dto.PetrolBillDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mariusz Kowalczuk
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetrolBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal value;
    private LocalDate date;
    private boolean settled;

    @ManyToOne
    private Driver driver;

    public PetrolBillDto toDto() {
        return PetrolBillDto.builder()
                .id(id)
                .value(value)
                .date(date)
                .settled(settled)
                //.driverDto(driver != null ? driver.toDto() : null)
                .build();
    }
}
