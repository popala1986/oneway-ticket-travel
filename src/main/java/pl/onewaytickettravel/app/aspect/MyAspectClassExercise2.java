package pl.onewaytickettravel.app.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * EN: Aspect class responsible for logging method calls within the service layer of the application.
 *     Using Spring AOP allows separation of technical concerns (like logging) from business logic.
 *
 * PL: Klasa aspektu odpowiedzialna za logowanie wywołań metod w warstwie serwisowej aplikacji.
 *     Zastosowanie Spring AOP pozwala oddzielić logikę techniczną (np. logowanie) od logiki biznesowej.
 */

@Aspect
@Component
public class MyAspectClassExercise2 {


    /**
     * EN: Pointcut expression that matches the execution of any method
     *     in any class within the 'pl.onewaytickettravel.app.service' package.
     *     It applies regardless of method name, return type, or parameters.
     *
     * PL: Punkt przecięcia obejmujący wykonanie dowolnej metody
     *     w dowolnej klasie pakietu 'pl.onewaytickettravel.app.service'.
     *     Działa niezależnie od nazwy metody, typu zwracanego czy parametrów.
     */
    @Pointcut("execution(* pl.onewaytickettravel.app.service.*.*(..))")
    public void serviceMethod() {}


    /**
     * EN: Advice that runs BEFORE the execution of any method matched by the 'serviceMethod' pointcut.
     *     Prints a message to the console before any service method is invoked.
     *
     * PL: Porada wykonywana PRZED każdą metodą pasującą do punktu przecięcia 'serviceMethod'.
     *     Wypisuje komunikat do konsoli przed wywołaniem dowolnej metody serwisowej.
     */
    @Before("serviceMethod()")
    public void showMeTextBeforeServiceMethod() {
        System.out.println(" =====>I will reserved the offer for the moment from service");
    }

}
