package pl.sda.fleetmanagementsystem.dto;

import lombok.*;

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
}
