package pl.onewaytickettravel.app.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.onewaytickettravel.app.auth.repository.UserRepository;

/**
 * Serwis odpowiedzialny za ładowanie danych użytkownika na potrzeby uwierzytelniania.
 * Implementuje interfejs UserDetailsService wymagany przez Spring Security.
 *
 * Service responsible for loading user details for authentication purposes.
 * Implements the UserDetailsService interface required by Spring Security.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    /**
     * Repozytorium użytkowników używane do wyszukiwania danych logowania.
     * User repository used to retrieve login credentials.
     */
    private final UserRepository userRepository;

    /**
     * Konstruktor inicjalizujący serwis z repozytorium użytkowników.
     *
     * Constructor initializing the service with the user repository.
     *
     * @param userRepository repozytorium użytkowników
     *                       user repository
     */
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Ładuje dane użytkownika na podstawie adresu e-mail.
     * Rzuca wyjątek UsernameNotFoundException, jeśli użytkownik nie istnieje.
     *
     * Loads user details based on the email address.
     * Throws UsernameNotFoundException if the user is not found.
     *
     * @param email adres e-mail użytkownika
     *              user's email address
     * @return obiekt UserDetails z danymi użytkownika
     *         UserDetails object containing user information
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
