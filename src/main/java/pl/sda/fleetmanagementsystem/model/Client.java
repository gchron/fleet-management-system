package pl.sda.fleetmanagementsystem.model;

import lombok.*;
import pl.sda.fleetmanagementsystem.dto.ClientDto;

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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;

    public ClientDto toDto(){
        return ClientDto.builder()
                .userDto(user !=null ? user.toDto():null)
                .build();
    }



}
