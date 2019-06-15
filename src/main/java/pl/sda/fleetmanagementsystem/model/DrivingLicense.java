package pl.sda.fleetmanagementsystem.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

/**
 * @author Mariusz Kowalczuk
 */
@Entity
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate expireDate;
    private String number;

    @OneToOne
    private Driver driver;



}
