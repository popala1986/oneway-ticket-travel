package pl.onewaytickettravel.app.configuration.initializer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;


/**
 * Konfiguracja bezpieczeństwa dla profilu testowego.
 * Tworzy użytkowników w pamięci na potrzeby testów integracyjnych.
 *
 * Security configuration for the test profile.
 * Creates in-memory users for integration testing purposes.
 */
@Configuration
@EnableWebSecurity
@Profile("test")
public class InMemoryConfig {

    /**
     * Tworzy menedżera użytkowników przechowującego dane w pamięci.
     *
     * Creates an in-memory user details manager.
     *
     * @return instancja InMemoryUserDetailsManager
     *         instance of InMemoryUserDetailsManager
     */
    @Bean
    public UserDetailsManager userDetailsManager(){
        return new InMemoryUserDetailsManager();
    }


    /**
     * Inicjalizuje dwóch użytkowników testowych po starcie aplikacji:
     * testAdmin z rolami USER i ADMIN oraz testUser z rolą USER.
     *
     * Initializes two test users at application startup:
     * testAdmin with USER and ADMIN roles, and testUser with USER role.
     *
     * @return InitializingBean wykonujący logikę tworzenia użytkowników
     *         InitializingBean executing user creation logic
     */
    @Bean
    public InitializingBean initializingBean(){
        return () -> {
            UserDetails user = User
                    .builder()
                    .username("testAdmin")
                    .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .password("password")
                    .roles("USER","ADMIN")
                    .build();
            userDetailsManager().createUser(user);
            UserDetails testUser = User
                    .builder()
                    .username("testUser")
                    .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .password("password")
                    .roles("USER")
                    .build();
            userDetailsManager().createUser(testUser);
        };
    }

}
