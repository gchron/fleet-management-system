package pl.sda.fleetmanagementsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.fleetmanagementsystem.controllers.CarController;
import pl.sda.fleetmanagementsystem.repository.CarRepository;
import pl.sda.fleetmanagementsystem.repository.UserRepository;
import pl.sda.fleetmanagementsystem.security.SecurityUserDetailsService;
import pl.sda.fleetmanagementsystem.service.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mariusz Kowalczuk
 */
@WebMvcTest(CarController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration

public class FleetManagementSystemApplicationTest {
    @TestConfiguration
    static class FleetManagementSystemApplicationTestConfig {
        @Bean
        public SecurityUserDetailsService securityUserDetailsService() {
            return new SecurityUserDetailsService(Mockito.mock(UserRepository.class));
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarFinder carFinder;
    @MockBean
    private CarService carService;
    @MockBean
    private DriverFinder driverFinder;
    @MockBean
    private UserFinder userFinder;
    @MockBean
    private DriverService driverService;
    @MockBean
    private CarRepository carRepository;




    @Test
    @WithMockUser(roles = "ADMINISTRATOR")
    public void findAllShouldListAllCars() throws Exception {
        this.mockMvc.perform(get("/cars")).andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("cars"));
    }
    @Test
    @WithMockUser(roles = "ADMINISTRATOR")
    public void findAllShouldBeForbidden() throws Exception {
        this.mockMvc.perform(get("/cars")).andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("cars"));
    }
    @Test
    @WithMockUser(roles = "ADMINISTRATOR")
    public void createShouldSaveANewCar() throws Exception{
        this.mockMvc.perform(get("/cars/create")).andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("car"));
//this.mockMvc.perform(post("/cars/create")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
//        .andExpect(jsonPath("$.model").exists());

    }

}