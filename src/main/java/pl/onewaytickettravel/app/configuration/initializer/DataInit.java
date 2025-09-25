package pl.onewaytickettravel.app.configuration.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.onewaytickettravel.app.auth.entities.User;
import pl.onewaytickettravel.app.auth.repository.UserRepository;
import java.util.List;

/**
 * Klasa inicjalizująca dane użytkowników przy starcie aplikacji.
 * Tworzy domyślnych użytkowników (admin i user), jeśli nie istnieją w bazie danych.
 *
 * Class responsible for initializing user data at application startup.
 * Creates default users (admin and user) if they do not exist in the database.
 */
@Configuration
public class DataInit {

    /**
     * Obiekt kodujący hasła użytkowników.
     * Password encoder used to hash user passwords.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Konstruktor inicjalizujący komponent z enkoderem haseł.
     *
     * Constructor initializing the component with a password encoder.
     *
     * @param passwordEncoder obiekt kodujący hasła
     *                        password encoder
     */
    public DataInit(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Tworzy bean CommandLineRunner, który uruchamia się po starcie aplikacji.
     * Sprawdza, czy użytkownicy admin@example.com i user@example.com istnieją — jeśli nie, dodaje ich do bazy.
     *
     * Creates a CommandLineRunner bean that runs after application startup.
     * Checks if admin@example.com and user@example.com exist — if not, inserts them into the database.
     *
     * @param userRepository repozytorium użytkowników
     *                       user repository
     * @param passwordEncoder enkoder haseł
     *                        password encoder
     * @return CommandLineRunner inicjalizujący dane użytkowników
     *         CommandLineRunner that initializes user data
     */
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