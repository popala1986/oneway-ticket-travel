package pl.onewaytickettravel.app.configuration.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.onewaytickettravel.app.auth.entities.User;
import pl.onewaytickettravel.app.auth.repository.UserRepository;
import java.util.List;

@Configuration
public class DataInit {

    private final PasswordEncoder passwordEncoder;

    public DataInit(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("Admin123!"));
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setEnabled(1);
                admin.setRoles(List.of("ROLE_ADMIN", "ROLE_USER"));
                userRepository.save(admin);
                System.out.println("✅ Utworzono użytkownika admin@example.com z hasłem 'Admin123!'");
            }

            if (userRepository.findByEmail("user@example.com").isEmpty()) {
                User user = new User();
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("User123!"));
                user.setFirstName("Normalny");
                user.setLastName("Użytkownik");
                user.setEnabled(1);
                user.setRoles(List.of("ROLE_USER"));
                userRepository.save(user);
                System.out.println("✅ Utworzono użytkownika user@example.com z hasłem 'User123!'");
            }
        };
    }

}