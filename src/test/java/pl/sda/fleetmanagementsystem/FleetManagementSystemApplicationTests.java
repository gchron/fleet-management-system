package pl.sda.fleetmanagementsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@PropertySource(value = {"application.properties", "application-ext.properties"},
        ignoreResourceNotFound = true)
@RunWith(SpringRunner.class)
@SpringBootTest
public class FleetManagementSystemApplicationTests {

    @Test
    public void contextLoads() {
    }

}
