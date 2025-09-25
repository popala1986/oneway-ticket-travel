package pl.onewaytickettravel.app.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.onewaytickettravel.app.auth.service.MyUserDetailsService;


/**
 * Konfiguracja zabezpieczeń aplikacji.
 * Definiuje ustawienia logowania, autoryzacji, szyfrowania haseł i dostępności zasobów.
 *
 * Application security configuration.
 * Defines login, authorization, password encoding, and resource access settings.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Serwis odpowiedzialny za ładowanie danych użytkownika.
     * User details service used for authentication.
     */
    private final MyUserDetailsService userDetailsService;

    /**
     * Konstruktor inicjalizujący konfigurację z serwisem użytkownika.
     *
     * Constructor initializing the configuration with user details service.
     *
     * @param userDetailsService serwis użytkownika
     *                           user details service
     */
    public SecurityConfig(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Bean odpowiedzialny za szyfrowanie haseł przy użyciu BCrypt.
     *
     * Bean responsible for password encryption using BCrypt.
     *
     * @return instancja PasswordEncoder
     *         instance of PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean konfigurujący dostawcę uwierzytelniania z użyciem DAO i szyfrowania haseł.
     *
     * Bean configuring DAO-based authentication provider with password encoding.
     *
     * @return instancja DaoAuthenticationProvider
     *         instance of DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    /**
     * Bean konfigurujący łańcuch filtrów zabezpieczeń.
     * Umożliwia dostęp do zasobów publicznych, obsługuje logowanie i wylogowanie.
     *
     * Bean configuring the security filter chain.
     * Allows access to public resources and handles login/logout.
     *
     * @param http obiekt konfiguracji zabezpieczeń HTTP
     *             HTTP security configuration object
     * @return SecurityFilterChain z pełną konfiguracją zabezpieczeń
     *         SecurityFilterChain with full security setup
     * @throws Exception w przypadku błędów konfiguracji
     *         if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/**",
                                "/swagger/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/css/**", "/js/**", "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .usernameParameter("email")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
