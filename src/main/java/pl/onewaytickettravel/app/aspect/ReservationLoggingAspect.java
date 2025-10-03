package pl.onewaytickettravel.app.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * EN: Aspect class responsible for logging method calls related to offer reservation.
 *     It uses Spring AOP to intercept specific method executions in the service and controller layers.
 *
 * PL: Klasa aspektu odpowiedzialna za logowanie wywołań metod związanych z rezerwacją ofert.
 *     Wykorzystuje Spring AOP do przechwytywania wybranych metod w warstwie serwisowej i kontrolerze.
 */
@Aspect
@Component
@Order(2)
public class ReservationLoggingAspect {

    /**
     * EN: Logs a message before any execution of the reserveOffer(...) method in ReservationService.
     * PL: Loguje komunikat przed każdym wywołaniem metody reserveOffer(...) w klasie ReservationService.
     */
    @Before("execution(* pl.onewaytickettravel.app.service.ReservationService.reserveOffer(..))")
    public void showMeTextBeforeYouReserveOfferFromService() {
        System.out.println("ReservationService.reserveOffer(...) is about to be executed.");
    }

    /**
     * EN: Logs a message before execution of reserveOffer(Long, String) in ReservationService.
     * PL: Loguje komunikat przed wywołaniem metody reserveOffer(Long, String) w klasie ReservationService.
     */
    @Before("execution(* pl.onewaytickettravel.app.service.ReservationService.reserveOffer(Long, String))")
    public void showMeTextBeforeYouReserveOfferFromControllerWithLongAndString() {
        System.out.println("reserveOffer(Long, String) in ReservationService is about to be executed.");
    }
}

