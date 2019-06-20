package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.fleetmanagementsystem.model.PetrolBill;
import pl.sda.fleetmanagementsystem.repository.PetrolBillRepository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */

@Service
@RequiredArgsConstructor
public class PetrolBillFinder {
    private final PetrolBillRepository petrolBillRepository;

    @Transactional
    public Set<PetrolBill> findUnsettledBillofDriver(Integer driverId) {
        Set<PetrolBill> unsettledBills = petrolBillRepository.findByDriver_Id(driverId).stream().filter(petrolBill -> !petrolBill.isSettled()).collect(Collectors.toSet());
        unsettledBills.stream().forEach(petrolBill -> petrolBill.setSettled(true));
        return unsettledBills;
    }


}
