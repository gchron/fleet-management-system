package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.model.Driver;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewDriverDto {
    private Integer id;
    private String userName;
    private String password;


    public Driver toEntity() {
        Driver driver = new Driver();
        driver.setId(id);
        driver.setUserName(userName);
        driver.setPassword(password);
        return driver;
    }
}
