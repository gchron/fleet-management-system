package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.model.PetrolBill;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class PetrolBillService {
    public BigDecimal computeValue(Set<PetrolBill> bills){
        return bills.stream().map(petrolBill -> petrolBill.getValue()).reduce(BigDecimal::add).orElseThrow(()-> new IllegalArgumentException("There are no unsettled payments of this driver"));


    }

}
