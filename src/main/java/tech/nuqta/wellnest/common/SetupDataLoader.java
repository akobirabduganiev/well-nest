package tech.nuqta.wellnest.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.nuqta.wellnest.entity.Role;
import tech.nuqta.wellnest.entity.User;
import tech.nuqta.wellnest.enums.RoleName;
import tech.nuqta.wellnest.repository.RoleRepository;
import tech.nuqta.wellnest.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SetupDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Sets up initial data in the application by creating default roles and users if they don't already exist.
     *
     * @param userRepository  the user repository
     * @param roleRepository  the role repository
     * @param passwordEncoder the password encoder
     */
    public SetupDataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * This method is responsible for running the setup data loading process.
     * It checks if the role and user repositories are empty, and if so, it creates and saves initial role and user entities.
     * The roles "USER", "ADMIN" and "VENDOR" are created and saved in the role repository.
     * Then, the user "Adminjon" and "Userbek" are created and saved in the user repository, with their respective roles.
     *
     * @param args The command line arguments passed to the application
     */
    @Override
    @Transactional
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            var user = new Role();
            user.setName(RoleName.USER);
            roleRepository.save(user);

            var admin = new Role();
            admin.setName(RoleName.ADMIN);
            roleRepository.save(admin);

        }
    }
}
