package pl.sda.fleetmanagementsystem.model;

import lombok.*;
import pl.sda.fleetmanagementsystem.dto.AdministratorDto;

import javax.persistence.*;

/**
 * @author Mariusz Kowalczuk
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;

    public AdministratorDto toDto(){
        return AdministratorDto.builder()
                .userDto(user !=null ? user.toDto():null)
                .build();
    }



}
