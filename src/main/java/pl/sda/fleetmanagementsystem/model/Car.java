package pl.sda.fleetmanagementsystem.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private String productionYear;
    private String mileage;
    private Double engineCapacity;

    @ManyToOne
    private Driver driver;

    @OneToOne
    private TechnicalInspection technicalInspection;

    @OneToMany
    private Set<CarAccident> carAccident;
}
