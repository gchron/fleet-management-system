package pl.sda.fleetmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@PropertySource(value = {"application.properties", "application-ext.properties"},
        ignoreResourceNotFound = true)
@EnableTransactionManagement
public class FleetManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleetManagementSystemApplication.class, args);
    }

}
