package pl.sda.fleetmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"application.properties", "application-ex.properties"},
        ignoreResourceNotFound = true)
public class FleetManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleetManagementSystemApplication.class, args);
    }

}
