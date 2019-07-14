//package pl.sda.fleetmanagementsystem.controllers
//
//import org.springframework.beans.factory.annotation.Autowired
//import pl.sda.fleetmanagementsystem.dto.CreateUserAssignment
//import pl.sda.fleetmanagementsystem.model.User
//import pl.sda.fleetmanagementsystem.repository.AdministratorRepository
//import pl.sda.fleetmanagementsystem.repository.ClientRepository
//import pl.sda.fleetmanagementsystem.repository.DriverRepository
//import pl.sda.fleetmanagementsystem.repository.UserRepository
//import pl.sda.fleetmanagementsystem.service.UserService
//import spock.lang.Specification
//
///**
// * @author Mariusz Kowalczuk
// */
//class UserControllerTest extends Specification {
//
//
//    @Autowired
//    private UserRepository userRepository
//    @Autowired
//    private DriverRepository driverRepository
//    @Autowired
//    private AdministratorRepository administratorRepository;
//    @Autowired
//    private ClientRepository clientRepository;
//    @Autowired
//    private UserService userService
//
//
//    def 'should create client driver or administrator, according to userRole'() {
//        given:
//        CreateUserAssignment createUserAssignment = new CreateUserAssignment(userName, "pass", "bla@bla.com", roleId)
//
//        when:
//        userService.register(createUserAssignment)
//        then:
//        def all = userRepository.findAll()
//
//        where:
//
//        roleId | userName | expected
//        1      | "driver" |
//
//
//    }
//}
