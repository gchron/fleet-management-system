package pl.sda.fleetmanagementsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Mariusz Kowalczuk
 */
@Getter
@Setter
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;


}
