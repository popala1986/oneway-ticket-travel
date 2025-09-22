package pl.onewaytickettravel.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.onewaytickettravel.app.model.SearchFilter;

@Aspect
@Component
public class OfferControllerAspect {

    private static final Logger log = LoggerFactory.getLogger(OfferControllerAspect.class);

    /**
     * Logs incoming search parameters from the web form.
     */

    @Before("execution(* pl.onewaytickettravel.app.controller.OfferController.handleSearch(..)) && args(filter,..)")
    public void logFormInput(SearchFilter filter) {
        log.info("🌐 Form submitted → continent={}, country={}, city={}, date={}, adults={}, departure={}, destination={}",
                filter.getContinentName(),
                filter.getCountryName(),
                filter.getCityName(),
                filter.getStartDate(),
                filter.getNumberOfAdults(),
                filter.getDepartureCity(),
                filter.getDestination());
    }

    /**
     * Measures execution time of the controller method.
     */

    @Around("execution(* pl.onewaytickettravel.app.controller.OfferController.handleSearch(..))")
    public Object measureControllerTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long duration = System.nanoTime() - start;
        log.info("📊 handleSearch executed in {} µs", duration / 1000);
        return result;
    }

    /**
     * Logs any exceptions thrown during controller execution.
     */

    @AfterThrowing(pointcut = "execution(* pl.onewaytickettravel.app.controller.OfferController.handleSearch(..))",
            throwing = "ex")
    public void logControllerError(Exception ex) {
        log.error("🚨 Exception in handleSearch: {}", ex.getMessage(), ex);
    }
}