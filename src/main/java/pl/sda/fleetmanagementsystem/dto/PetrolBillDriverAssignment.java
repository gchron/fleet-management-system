package pl.sda.fleetmanagementsystem.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Mariusz Kowalczuk
 */
@Data
public class PetrolBillDriverAssignment {
    private BigDecimal value;
    private Integer driverId;
}
