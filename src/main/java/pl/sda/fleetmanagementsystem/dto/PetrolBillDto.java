package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.PetrolBill;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetrolBillDto {

    private Integer id;
    private BigDecimal value;
    private LocalDate date;
    private boolean settled;
    private DriverDto driverDto;

    public PetrolBill toEntity() {
        return PetrolBill.builder()
                .value(value)
                .date(date)
                .driver(driverDto != null ? driverDto.toEntity() : null)
                .settled(settled)
                .build();
    }
}
