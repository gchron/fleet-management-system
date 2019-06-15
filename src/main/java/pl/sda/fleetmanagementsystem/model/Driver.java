package pl.sda.fleetmanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Entity
public class Driver extends User {

    @OneToOne
    private DrivingLicense drivingLicense;

    @OneToMany
    private Set<Car> cars;

}
