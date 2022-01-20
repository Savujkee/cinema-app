package cinema.config;

import cinema.model.Role;
import cinema.model.User;
import cinema.model.enums.RoleName;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(RoleName.USER);
        roleService.add(userRole);
        User user = new User();
        user.setEmail("admin@somemail.org");
        user.setPassword("qwerty");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
    }
}
