package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public PetrolBill toEntity() {
        return PetrolBill.builder()
                .id(id)
                .value(value)
                .date(date)
                .build();
    }
}
