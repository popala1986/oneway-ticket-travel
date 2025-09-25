package pl.onewaytickettravel.app.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.onewaytickettravel.app.auth.entities.User;

import java.util.Optional;

/**
 * Repozytorium JPA dla encji User.
 * Umożliwia operacje CRUD oraz wyszukiwanie użytkowników po adresie e-mail.
 *
 * JPA repository for the User entity.
 * Provides CRUD operations and user lookup by email address.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Wyszukuje użytkownika na podstawie adresu e-mail.
     *
     * Finds a user by their email address.
     *
     * @param email adres e-mail użytkownika
     *              user's email address
     * @return Optional zawierający użytkownika, jeśli istnieje
     *         Optional containing the user if found
     */

    Optional<User> findByEmail(String email);


}
