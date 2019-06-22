package pl.sda.fleetmanagementsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */

@Getter
@Setter
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles;
}

