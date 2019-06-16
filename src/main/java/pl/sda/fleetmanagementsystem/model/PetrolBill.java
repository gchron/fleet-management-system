package pl.sda.fleetmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.dto.PetrolBillDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mariusz Kowalczuk
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
public class PetrolBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal value;
    private LocalDate date;

    public PetrolBillDto toDto(){
        return PetrolBillDto.builder()
                .id(id)
                .value(value)
                .date(date)
                .build();
    }
}
