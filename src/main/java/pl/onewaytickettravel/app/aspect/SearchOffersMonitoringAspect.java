package pl.onewaytickettravel.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * EN: Aspect class responsible for monitoring the execution of the searchOffers(SearchFilter) method in OfferService.
 *     Uses Spring AOP and JoinPoint to log method name and arguments before execution.
 *     Marked with @Order(3) to ensure it runs after general service-level aspects.
 *
 * PL: Klasa aspektu odpowiedzialna za monitorowanie wykonania metody searchOffers(SearchFilter) w klasie OfferService.
 *     Wykorzystuje Spring AOP oraz JoinPoint do logowania nazwy metody i argumentów przed jej wykonaniem.
 *     Oznaczona adnotacją @Order(3), co zapewnia jej wykonanie po ogólnych aspektach warstwy serwisowej.
 */
@Aspect
@Component
@Order(3)
public class SearchOffersMonitoringAspect {

    /**
     * EN: Pointcut targeting the searchOffers(SearchFilter) method in OfferService.
     * PL: Punkt przecięcia dla metody searchOffers(SearchFilter) w klasie OfferService.
     */
    @Pointcut("execution(* pl.onewaytickettravel.app.service.OfferService.searchOffers(..))")
    public void searchOffersMethod() {}

    /**
     * EN: Logs method name and arguments before execution.
     * PL: Loguje nazwę metody i argumenty przed jej wykonaniem.
     */
    @Before("searchOffersMethod()")
    public void logMethodDetails(JoinPoint joinPoint) {
        System.out.println("🔍 Method: " + joinPoint.getSignature());
        System.out.println("📦 Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }
}