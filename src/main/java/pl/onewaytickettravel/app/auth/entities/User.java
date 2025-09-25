package pl.onewaytickettravel.app.auth.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reprezentuje użytkownika aplikacji z danymi logowania i rolami.
 * Implementuje interfejs UserDetails wymagany przez Spring Security.
 *
 * Represents an application user with login credentials and roles.
 * Implements the UserDetails interface required by Spring Security.
 */

@Entity
@Table(name = "users")
public class User implements UserDetails {

    /**
     * Unikalny identyfikator użytkownika.
     * Unique identifier of the user.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Adres e-mail użytkownika, używany jako login.
     * User's email address used as login.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Hasło użytkownika w postaci zaszyfrowanej.
     * User's encrypted password.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Imię użytkownika.
     * User's first name.
     */
    private String firstName;

    /**
     * Nazwisko użytkownika.
     * User's last name.
     */
    private String lastName;

    /**
     * Numer telefonu użytkownika.
     * User's phone number.
     */
    private String phone;

    /**
     * Flaga aktywności konta (1 = aktywne, 0 = nieaktywne).
     * Account enabled flag (1 = active, 0 = inactive).
     */
    private int enabled;

    /**
     * Lista ról przypisanych do użytkownika.
     * List of roles assigned to the user.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;


    /**
     * Zwraca kolekcję uprawnień użytkownika na podstawie jego ról.
     * Returns a collection of user's authorities based on assigned roles.
     *
     * @return lista obiektów GrantedAuthority
     *         list of GrantedAuthority objects
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Zwraca hasło użytkownika.
     * Returns the user's password.
     *
     * @return hasło
     *         password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Zwraca nazwę użytkownika (adres e-mail).
     * Returns the username (email address).
     *
     * @return email
     *         email
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Informuje, czy konto użytkownika nie wygasło.
     * Indicates whether the user's account is non-expired.
     *
     * @return true jeśli konto jest aktywne
     *         true if account is active
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Informuje, czy konto użytkownika nie jest zablokowane.
     * Indicates whether the user's account is non-locked.
     *
     * @return true jeśli konto nie jest zablokowane
     *         true if account is not locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Informuje, czy dane logowania użytkownika nie wygasły.
     * Indicates whether the user's credentials are non-expired.
     *
     * @return true jeśli dane są aktualne
     *         true if credentials are valid
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Informuje, czy konto użytkownika jest aktywne.
     * Indicates whether the user's account is enabled.
     *
     * @return true jeśli enabled = 1
     *         true if enabled = 1
     */
    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}