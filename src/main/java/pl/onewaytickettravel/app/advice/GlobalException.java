package pl.onewaytickettravel.app.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.onewaytickettravel.app.exception.OfferNotFoundException;

/**
 * Globalna klasa obsługi wyjątków w aplikacji.
 * Przechwytuje określone wyjątki i zwraca ustandaryzowane odpowiedzi HTTP z informacją o błędzie.
 *
 * Global exception handler for the application.
 * Captures specific exceptions and returns standardized HTTP error responses.
 */
@ControllerAdvice
public class GlobalException {

    /**
     * Obsługuje wyjątek OfferNotFoundException, gdy oferta nie zostanie znaleziona.
     * Zwraca obiekt ProblemDetail z kodem HTTP 404 i komunikatem błędu.
     *
     * Handles OfferNotFoundException when an offer is not found.
     * Returns a ProblemDetail object with HTTP 404 status and error message.
     *
     * @param e wyjątek OfferNotFoundException zawierający komunikat o błędzie
     *          the thrown OfferNotFoundException containing the error message
     * @return ProblemDetail z informacją o błędzie i statusem NOT_FOUND
     *         ProblemDetail containing error details and NOT_FOUND status
     */
    @ExceptionHandler(OfferNotFoundException.class)
    public ProblemDetail handleOfferNotFoundException(OfferNotFoundException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
